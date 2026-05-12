package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/member/{memberId}/store/{storeId}")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> postReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request
    ) {
        // 서비스 호출
        Review review = reviewService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(
                ReviewResponseDTO.CreateReviewResultDTO.builder()
                        .reviewId(review.getId())
                        .createdAt(review.getCreatedAt())
                        .build()
        );
    }
}
