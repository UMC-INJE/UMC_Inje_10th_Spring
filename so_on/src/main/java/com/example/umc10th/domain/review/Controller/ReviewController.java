package com.example.umc10th.domain.review.Controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class ReviewController {

    @PostMapping("/{userId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long userId,
            @RequestBody ReviewRequestDTO.ReviewDto request) {
        return ApiResponse.onSuccess("리뷰가 성공적으로 등록되었습니다. (ID: 100)");
    }
}