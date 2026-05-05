package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResultDTO {
        private Long userId;
        private String userLoginId;
        private String userNickname;
        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageDTO {
        private String userNickname;
        private String userEmail;
        private String userPhone;
        private Integer userPoint;
    }
}
