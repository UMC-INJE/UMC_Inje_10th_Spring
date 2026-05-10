package com.example.umc10th.domain.user.Service;

import com.example.umc10th.domain.mission.Repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.review.Repository.ReviewRepository;
import com.example.umc10th.domain.user.Entity.User;
import com.example.umc10th.domain.user.Repository.UserRepository;
import com.example.umc10th.domain.user.code.UserErrorCode;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.domain.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserResponseDTO.MyPageDto getMyPage(Long userId) {
        User user = getUser(userId);
        boolean phoneVerified = user.getPhoneNumber() != null && !user.getPhoneNumber().isBlank();

        return UserResponseDTO.MyPageDto.builder()
                .userId(user.getId())
                .nickname(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .phoneVerificationStatus(phoneVerified ? "인증완료" : "미인증")
                .totalPoints(user.getPoint())
                .reviewCount(reviewRepository.countByUserId(userId))
                .profileImageUrl(user.getProfileUrl())
                .build();
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
