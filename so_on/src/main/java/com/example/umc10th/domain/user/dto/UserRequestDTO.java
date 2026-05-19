package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank(message = "이름은 필수입니다.")
        String name;

        @NotBlank(message = "성별은 필수입니다.")
        String gender;

        @NotBlank(message = "생년월일은 필수입니다.")
        String birth;

        @NotBlank(message = "주소는 필수입니다.")
        String address;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email;

        @NotNull(message = "선호 카테고리는 필수입니다.")
        List<String> preferCategories;

        @NotNull(message = "동의한 약관 ID는 필수입니다.")
        List<Long> agreedTermsIds;
    }
}