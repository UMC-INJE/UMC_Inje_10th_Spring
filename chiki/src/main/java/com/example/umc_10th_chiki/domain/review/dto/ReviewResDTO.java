package com.example.umc_10th_chiki.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WriteResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }
}