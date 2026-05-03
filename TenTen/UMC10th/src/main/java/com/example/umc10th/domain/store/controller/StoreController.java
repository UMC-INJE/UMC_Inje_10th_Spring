package com.example.umc10th.domain.store.controller;
import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResDTO.ReviewResultDTO> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestBody StoreReqDTO.CreateReviewDTO request
    ) {
        StoreResDTO.ReviewResultDTO response = StoreResDTO.ReviewResultDTO.builder()
                .storeReviewId(1L)
                .storeId(storeId)
                .myMissionId(request.getMyMissionId())
                .storeReviewRating(request.getStoreReviewRating())
                .storeReviewContent(request.getStoreReviewContent())
                .message("리뷰가 작성되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }
}
