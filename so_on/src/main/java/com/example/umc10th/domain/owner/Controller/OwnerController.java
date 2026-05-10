package com.example.umc10th.domain.owner.Controller;

import com.example.umc10th.domain.review.Service.ReviewService;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 답글 등록", description = "사장님이 리뷰에 답글을 등록합니다.")
    @PostMapping("/{ownerId}/reviews/{reviewId}/reply")
    public ApiResponse<ReviewResponseDTO.ReplyResultDto> createReply(
            @PathVariable Long ownerId,
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDTO.ReplyCreateDto request) {
        return ApiResponse.onSuccess(reviewService.createReply(ownerId, reviewId, request));
    }
}
