package com.example.umc_10th_chiki.domain.review.controller;

import com.example.umc_10th_chiki.domain.review.dto.ReviewResDTO;
import com.example.umc_10th_chiki.domain.review.service.ReviewQueryService;
import com.example.umc_10th_chiki.domain.review.entity.Review;
import com.example.umc_10th_chiki.domain.review.converter.ReviewConverter;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 내가 생성한 리뷰들 조회하기 (커서 기반 페이지네이션)
    @GetMapping("/api/v1/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewSliceDTO> getMyReviews(
          @PathVariable(name = "memberId") Long memberId,
          @RequestParam(name = "cursor", required = false) String cursor, // "id:15" 또는 "score:4.5_12"
          @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        Slice<Review> reviewSlice = reviewQueryService.getMyReviews(memberId, cursor, size);

        String sortType = "id"; // 기본값은 최신순
        if (cursor != null && cursor.startsWith("score")) {
            sortType = "score"; // 커서가 score로 시작하면 별점순
        }
        ReviewResDTO.ReviewSliceDTO response = ReviewConverter.toReviewSliceDTO(reviewSlice, sortType);
        return ApiResponse.onSuccess(response);
    }
}