package com.example.umc_10th_chiki.domain.member.service;

import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;
    // [6주차 미션] 마이페이지 조회
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
    }
}