package com.example.umc10th.domain.user.Service;

import com.example.umc10th.domain.mission.Repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.review.Repository.ReviewRepository;
import com.example.umc10th.domain.user.Entity.User;
import com.example.umc10th.domain.user.Repository.UserRepository;
import com.example.umc10th.domain.user.code.UserErrorCode;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.global.security.JwtTokenProvider;
import com.example.umc10th.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private static final int HOME_MISSION_PAGE_SIZE = 10;
    private static final int MISSION_GOAL_COUNT = 10;
    private static final int MISSION_GOAL_REWARD_POINT = 1000;

    private final UserRepository userRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponseDTO.SignupResultDto signup(UserRequestDTO.SignupDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        User savedUser = userRepository.save(User.builder()
                .name(deriveNickname(request.getEmail()))
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .point(0)
                .build());

        return UserResponseDTO.SignupResultDto.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public UserResponseDTO.LoginResultDto login(UserRequestDTO.LoginDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.createAccessToken(principal);

        return UserResponseDTO.LoginResultDto.builder()
                .userId(principal.getUserId())
                .email(principal.getEmail())
                .tokenType("Bearer")
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusNanos(jwtTokenProvider.getAccessTokenValidityMs() * 1_000_000))
                .build();
    }

    public UserResponseDTO.HomeDto getHome(Long userId, String location, Integer page) {
        User user = getUser(userId);

        var missionPage = memberMissionRepository.findUserMissions(
                userId,
                MissionStatus.CHALLENGING,
                PageRequest.of(page == null || page < 1 ? 0 : page - 1, HOME_MISSION_PAGE_SIZE)
        );

        var homeMissionList = missionPage.getContent().stream()
                .map(memberMission -> UserResponseDTO.HomeMissionDto.builder()
                        .memberMissionId(memberMission.getId())
                        .missionId(memberMission.getMission().getId())
                        .storeName(memberMission.getMission().getStore().getName())
                        .storeCategory(memberMission.getMission().getStore().getRegion() == null ? null : memberMission.getMission().getStore().getRegion().getName())
                        .rewardPoint(memberMission.getMission().getPoint())
                        .deadline(memberMission.getMission().getDeadline() == null ? null : memberMission.getMission().getDeadline().toString())
                        .missionContent(memberMission.getMission().getConditionalText())
                        .status(memberMission.getStatus().name())
                        .build())
                .toList();

        long completedMissionCount = memberMissionRepository.countByUserIdAndStatus(userId, MissionStatus.COMPLETED);

        return UserResponseDTO.HomeDto.builder()
                .userName(user.getName())
                .location(resolveLocation(user, location))
                .totalPoints(user.getPoint())
                .completedMissionCount((int) completedMissionCount)
                .missionGoalCount(MISSION_GOAL_COUNT)
                .rewardPoint(MISSION_GOAL_REWARD_POINT)
                .ongoingMissionPage(UserResponseDTO.HomeMissionPageDto.builder()
                        .listSize(homeMissionList.size())
                        .totalPage(missionPage.getTotalPages())
                        .totalElements(missionPage.getTotalElements())
                        .currentPage(missionPage.getNumber() + 1)
                        .isLast(missionPage.isLast())
                        .missionList(homeMissionList)
                        .build())
                .build();
    }

    public UserResponseDTO.MyPageDto getMyPage(Long requestUserId, UserPrincipal principal) {
        if (principal == null || !requestUserId.equals(principal.getUserId())) {
            throw new UserException(UserErrorCode.USER_FORBIDDEN);
        }

        User user = getUser(principal.getUserId());
        boolean phoneVerified = user.getPhoneNumber() != null && !user.getPhoneNumber().isBlank();

        return UserResponseDTO.MyPageDto.builder()
                .userId(user.getId())
                .nickname(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .phoneVerificationStatus(phoneVerified ? "인증완료" : "미인증")
                .totalPoints(user.getPoint())
                .reviewCount(reviewRepository.countByUserId(user.getId()))
                .profileImageUrl(user.getProfileUrl())
                .build();
    }

    private String deriveNickname(String email) {
        String nickname = email.contains("@") ? email.substring(0, email.indexOf("@")) : email;
        return nickname.length() > 20 ? nickname.substring(0, 20) : nickname;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }

    private String resolveLocation(User user, String location) {
        if (location != null && !location.isBlank()) {
            return location;
        }
        return user.getAddress() == null ? null : user.getAddress().name();
    }
}
