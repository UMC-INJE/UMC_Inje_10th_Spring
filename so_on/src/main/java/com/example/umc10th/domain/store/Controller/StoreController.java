package com.example.umc10th.domain.store.Controller;

import com.example.umc10th.domain.review.Service.ReviewService;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/stores")
public class StoreController {

    private final ReviewService reviewService;

    @Operation(summary = "점포 리뷰 목록 조회", description = "점포관리 리뷰 탭에서 사용하는 리뷰 목록을 페이징 조회합니다.")
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.StoreReviewPageDto> getStoreReviews(
            @PathVariable Long ownerId,
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page) {
        return ApiResponse.onSuccess(reviewService.getStoreReviews(ownerId, storeId, page));
    }
}
