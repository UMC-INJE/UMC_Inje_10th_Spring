package com.example.umc10th.domain.preference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PreferenceResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PreferenceDTO {
        private List<Long> categoryIds;
        private String message;
    }
}