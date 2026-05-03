package com.example.umc10th.domain.user.dto;
import lombok.Getter;

public class UserReqDTO {
    @Getter
    public static class SignupDTO {
        private Long regionId;
        private String userLoginId;
        private String userPassword;
        private String userName;
        private String userEmail;
        private String userGender;
        private String userBirthdate;
        private String userAddress;
        private String userPhone;
        private String userNickname;
    }
}
