package com.example.umc_10th_chiki.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class WriteDTO {

        @JsonProperty("user_id")
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        // 별점 검증 (1.0 ~ 5.0)
        @NotNull(message = "별점은 필수 입력 항목입니다.")
        @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
        private Float score;

        // 리뷰 내용 검증 (공백 불가)
        @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
        private String text;

        // 사진은 필수값이 아닐 수 있으므로 (기획에 따라 다름) 일단 검증 생략
        // 만약 필수라면 @NotBlank를 붙여야 함.
        @JsonProperty("photo_url")
        private String photoUrl;
    }
}