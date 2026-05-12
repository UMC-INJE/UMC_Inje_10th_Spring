package com.example.umc10th.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

public class UserReqDTO {

    @Getter
    public static class SignUpDTO {

        @Schema(description = "지역 ID", example = "1")
        @JsonProperty("region_id")
        private Long regionId;

        @Schema(description = "로그인 ID", example = "taewan123")
        @JsonProperty("user_login_id")
        private String userLoginId;

        @Schema(description = "비밀번호", example = "1234")
        @JsonProperty("user_password")
        private String userPassword;

        @Schema(description = "이름", example = "김태완")
        @JsonProperty("user_name")
        private String userName;

        @Schema(description = "이메일", example = "taewan@test.com")
        @JsonProperty("user_email")
        private String userEmail;

        @Schema(description = "성별", example = "MALE")
        @JsonProperty("user_gender")
        private String userGender;

        @Schema(description = "생년월일", example = "2000-01-01")
        @JsonProperty("user_birthdate")
        private LocalDate userBirthdate;

        @Schema(description = "주소", example = "부산광역시 북구")
        @JsonProperty("user_address")
        private String userAddress;

        @Schema(description = "전화번호", example = "010-1234-5678")
        @JsonProperty("user_phone")
        private String userPhone;

        @Schema(description = "닉네임", example = "nickname12")
        @JsonProperty("user_nickname")
        private String userNickname;
    }
}