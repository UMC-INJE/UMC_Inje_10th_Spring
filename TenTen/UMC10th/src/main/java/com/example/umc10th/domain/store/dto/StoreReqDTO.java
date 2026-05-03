package com.example.umc10th.domain.store.dto;
import lombok.Getter;

public class StoreReqDTO {
    @Getter
    public static class CreateReviewDTO {
        private Long myMissionId;
        private Double storeReviewRating;
        private String storeReviewContent;
    }
}
