package com.example.umc10th.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewResultDTO {

        @Schema(example = "1")
        @JsonProperty("store_review_id")
        private Long storeReviewId;

        @Schema(example = "12")
        @JsonProperty("store_id")
        private Long storeId;

        @Schema(example = "2")
        @JsonProperty("my_mission_id")
        private Long myMissionId;

        @Schema(example = "4.5")
        @JsonProperty("store_review_rating")
        private Float storeReviewRating;

        @Schema(example = "정말 맛있어요! 사장님도 친절하세요.")
        @JsonProperty("store_review_content")
        private String storeReviewContent;
    }
}