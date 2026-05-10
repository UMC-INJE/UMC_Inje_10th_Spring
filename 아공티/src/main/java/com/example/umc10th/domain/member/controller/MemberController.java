package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회
    @GetMapping("/me/{memberId}") // 실제로는 토큰을 쓰지만 지금은 ID를 경로로 받습니다.
    public ApiResponse<MemberResDTO.GetInfo> getMyInfo(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberService.getInfo(memberId));
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/me/{memberId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<Review> reviewPage = memberService.getMyReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewPage));
    }
}