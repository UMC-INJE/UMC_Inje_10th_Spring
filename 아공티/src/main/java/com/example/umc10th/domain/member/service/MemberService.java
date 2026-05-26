package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder; // 시큐리티 암호화 인코더
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    // 마이페이지용 리뷰 목록 조회
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 페이지는 0부터 시작, 한 페이지에 10개씩 최신순 정렬
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10, Sort.by("createdAt").descending()));
    }

    public Page<MemberMission> getMyMissionList(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId)//유저 있는지 검증
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 최신순 정렬을 추가한 페이징
        return memberMissionRepository.findAllByMemberAndStatus(
                member,
                status,
                PageRequest.of(page, 10, Sort.by("createdAt").descending())
                //최신순으로 10페이지씩
        );
    }
    // 회원가입
    @Transactional
    public Member joinMember(MemberReqDTO.JoinDTO request) {

        // 이메일 중복 체크
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS); // 이미 존재하면 에러 메세지
        }

        // 비밀번호를 BCrypt 솔트 방식으로 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 빌더 패턴으로 Member 엔티티 조립
        Member member = Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailaddress(request.getDetailAddress())
                .email(request.getEmail())
                .password(encodedPassword) // 암호화된 비번
                .point(0)                  // 가입 초기 포인트초기화
                .build();

        // DB에 저장 후 엔티티 반환
        return memberRepository.save(member);
    }


}
