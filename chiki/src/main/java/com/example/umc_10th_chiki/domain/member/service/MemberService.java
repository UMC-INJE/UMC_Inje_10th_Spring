package com.example.umc_10th_chiki.domain.member.service;

import com.example.umc_10th_chiki.domain.member.dto.MemberReqDTO;
import com.example.umc_10th_chiki.domain.member.dto.MemberResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    // 회원가입 비즈니스 로직
    @Transactional
    public MemberResDTO.JoinResultDTO join(MemberReqDTO.JoinDTO request) {

        // 1. 요청으로 들어온 데이터 확인 (예시)
        // String name = request.getUserName();
        // String email = request.getUserEmail();

        // 2. 실제로는 여기서 Member 객체를 만들어 DB(Repository)에 저장하는 로직이 들어갑니다.

        // 3. 처리가 끝나면 그 결과를 다시 Response DTO에 담아서 컨트롤러로 돌려보냅니다.
        return MemberResDTO.JoinResultDTO.builder()
                .memberId(1L) // 임시 가입 ID
                .createdAt(LocalDateTime.now()) // 가입 시간
                .build();
    }
}