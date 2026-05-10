package com.example.umc_10th_chiki.domain.member.controller;

import com.example.umc_10th_chiki.domain.member.dto.MemberReqDTO;
import com.example.umc_10th_chiki.domain.member.dto.MemberResDTO;
import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.service.MemberCommandService;
import com.example.umc_10th_chiki.domain.member.service.MemberQueryService;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("")
    public ApiResponse<MemberResDTO.MyPageDTO> join(@RequestBody MemberReqDTO.JoinDTO request) {
        Member member = memberCommandService.join(request);
        return ApiResponse.onSuccess(MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build());
    }

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