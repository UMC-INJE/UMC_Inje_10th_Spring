package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @Operation(
            summary = "리뷰 작성 API",
            description = "사진 업로드를 제외한 리뷰 작성 API입니다."
    )
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResDTO.CreateReviewResultDTO> createReview(

            @Parameter(description = "Access Token", example = "Bearer accessToken")
            @RequestHeader(value = "Authorization", required = false) String authorization,

            @Parameter(description = "가게 ID", example = "12")
            @PathVariable Long storeId,

            @RequestBody StoreReqDTO.CreateReviewDTO request
    ) {

        Long userId = 1L;

        return ApiResponse.onSuccess(
                storeService.createReview(userId, storeId, request)
        );
    }
}