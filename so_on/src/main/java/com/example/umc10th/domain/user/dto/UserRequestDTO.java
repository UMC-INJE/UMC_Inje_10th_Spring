package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class SignupDto {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password;
    }

    @Getter
    public static class LoginDto {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password;
    }

    @Getter
    public static class MyPageRequestDto {
        @NotNull(message = "사용자 ID는 필수입니다.")
        Long userId;
    }
}
