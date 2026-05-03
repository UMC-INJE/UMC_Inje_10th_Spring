package com.example.umc10th.domain.user.dto;

import lombok.Getter;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto {
        String name;
        String gender;
        String birth;
        String address;
        String email;
        List<String> preferCategories;
        List<Long> agreedTermsIds;
    }
}