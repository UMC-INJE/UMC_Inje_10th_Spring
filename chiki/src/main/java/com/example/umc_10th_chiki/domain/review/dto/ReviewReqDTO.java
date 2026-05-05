package com.example.umc_10th_chiki.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class WriteDTO {
        // 향후 JWT 도입 시 Request Body에서 제거하고 헤더(Token)에서 추출하는 것을 권장합니다!
        @JsonProperty("user_id")
        private Long userId;

        private Float score;

        private String text;

        @JsonProperty("photo_url")
        private String photoUrl;
    }
}