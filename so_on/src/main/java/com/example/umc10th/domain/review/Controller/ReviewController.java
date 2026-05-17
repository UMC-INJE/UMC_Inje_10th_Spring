package com.example.umc10th.domain.review.Controller;

import com.example.umc10th.domain.review.Service.ReviewService;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long userId,
            @Valid @RequestBody ReviewRequestDTO.ReviewDto request) {
        return ApiResponse.onSuccess("리뷰가 성공적으로 등록되었습니다. (ID: 100)");
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "커서 기반 페이지네이션으로 사용자가 작성한 리뷰를 조회합니다. query: id 또는 star")
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResponseDTO.MemberReviewPageDto> getMemberReviews(
            @PathVariable Long memberId,
            @Valid @ModelAttribute ReviewRequestDTO.MemberReviewRequestDto request) {
        return ApiResponse.onSuccess(reviewService.getMemberReviews(memberId, request));
    }
}