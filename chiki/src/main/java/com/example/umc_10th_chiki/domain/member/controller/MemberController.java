package com.example.umc_10th_chiki.domain.member.controller;

import com.example.umc_10th_chiki.domain.member.dto.MemberReqDTO;
import com.example.umc_10th_chiki.domain.member.dto.MemberResDTO;
import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.service.MemberCommandService;
import com.example.umc_10th_chiki.domain.member.service.MemberQueryService;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // [8주차 미션]: 회원가입 API (Public API) - 시큐리티 통과 및 DTO 반환 적용
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody @Valid MemberReqDTO.JoinDTO request
    ) {
        MemberResDTO.JoinResultDTO result = memberCommandService.join(request);
        return ApiResponse.onSuccess(result);
    }

    // 마이페이지 조회 API (Private API - 로그인 필요)
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@PathVariable("memberId") Long memberId) {
        Member member = memberQueryService.getMember(memberId);
        return ApiResponse.onSuccess(MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build());
    }
}