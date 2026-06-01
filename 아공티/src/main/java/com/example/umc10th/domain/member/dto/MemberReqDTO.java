package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDTO {
        @NotBlank @Email
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotNull
        private Gender gender;

        @NotNull
        private LocalDate birth;

        @NotNull
        private Address address;

        @NotBlank
        private String detailAddress;

        // 선호 음식 카테고리 ID 리스트 (중복 체크)
        private List<Long> preferCategoryIds;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {
        @NotBlank @Email
        private String email;

        @NotBlank
        private String password;
    }

    public record GetInfo(
            Long id
    ){}
}




