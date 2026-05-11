package com.example.umc10th.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResultDTO {

        @Schema(example = "1")
        @JsonProperty("user_id")
        private Long userId;

        @Schema(example = "taewan123")
        @JsonProperty("user_login_id")
        private String userLoginId;

        @Schema(example = "nickname12")
        @JsonProperty("user_nickname")
        private String userNickname;

        @Schema(example = "회원가입에 성공하였습니다.")
        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageDTO {

        @Schema(example = "1")
        @JsonProperty("user_id")
        private Long userId;

        @Schema(example = "nickname12")
        @JsonProperty("user_nickname")
        private String userNickname;

        @Schema(example = "taewan@test.com")
        @JsonProperty("user_email")
        private String userEmail;

        @Schema(example = "010-1234-5678")
        @JsonProperty("user_phone")
        private String userPhone;

        @Schema(example = "2500")
        @JsonProperty("user_point")
        private Integer userPoint;
    }
}