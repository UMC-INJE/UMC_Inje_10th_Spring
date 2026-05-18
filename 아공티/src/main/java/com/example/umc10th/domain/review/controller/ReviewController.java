package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
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
        Review review = reviewService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(
                ReviewResponseDTO.CreateReviewResultDTO.builder()
                        .reviewId(review.getId())
                        .createdAt(review.getCreatedAt())
                        .build()
        );
    }
    // 내가 생성한 리뷰들
    @GetMapping("/member/{memberId}")
    public ApiResponse<ReviewResponseDTO.MyReviewCursorListDTO> getMyReviews(
            @PathVariable Long memberId,
            @ModelAttribute ReviewRequestDTO.MyReviewListDTO request
    ) {

        Slice<Review> reviewSlice = reviewService.getMyReviewList(memberId, request);

        return ApiResponse.onSuccess(ReviewConverter.toMyReviewCursorListDTO(reviewSlice));
    }
}
