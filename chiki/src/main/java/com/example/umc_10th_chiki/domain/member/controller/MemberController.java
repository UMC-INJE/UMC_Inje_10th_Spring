package com.example.umc_10th_chiki.domain.member.controller;

import com.example.umc_10th_chiki.domain.member.dto.MemberReqDTO;
import com.example.umc_10th_chiki.domain.member.dto.MemberResDTO;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    // private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody MemberReqDTO.JoinDTO request
    ) {
        // 실제로는 return ApiResponse.onSuccess(memberService.join(request)); 형태가 됩니다.
        return ApiResponse.onSuccess(null);
    }
}