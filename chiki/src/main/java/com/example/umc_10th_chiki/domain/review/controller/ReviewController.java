package com.example.umc_10th_chiki.domain.review.controller;

import com.example.umc_10th_chiki.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th_chiki.domain.review.dto.ReviewResDTO;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    // private final ReviewService reviewService;

    @PostMapping("/{store_id}/reviews")
    public ApiResponse<ReviewResDTO.WriteResultDTO> writeReview(
            @PathVariable("store_id") Long storeId,
            @RequestBody ReviewReqDTO.WriteDTO request
    ) {
        return ApiResponse.onSuccess(null);
    }
}