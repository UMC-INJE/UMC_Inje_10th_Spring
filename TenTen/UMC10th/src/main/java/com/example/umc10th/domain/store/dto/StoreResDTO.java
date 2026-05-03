package com.example.umc10th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewResultDTO {
        private Long storeReviewId;
        private Long storeId;
        private Long myMissionId;
        private Double storeReviewRating;
        private String storeReviewContent;
        private String message;
    }
}
