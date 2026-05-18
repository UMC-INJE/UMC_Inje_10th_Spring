package com.example.umc10th.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

public class StoreReqDTO {

    @Getter
    public static class CreateReviewDTO {

        @Schema(description = "내 미션 ID", example = "2")
        @JsonProperty("my_mission_id")
        @NotNull(message = "내 미션 ID는 필수입니다.")
        private Long myMissionId;

        @Schema(description = "리뷰 평점", example = "4.5")
        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 0, message = "별점은 0점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
        @JsonProperty("store_review_rating")
        private Float storeReviewRating;

        @Schema(description = "리뷰 내용", example = "정말 맛있어요! 사장님도 친절하세요.")
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 200, message = "리뷰는 200자 이하로 작성해주세요.")
        @JsonProperty("store_review_content")
        private String storeReviewContent;
    }
}