package com.example.umc10th.domain.preference.dto;

import lombok.Getter;

import java.util.List;

public class PreferenceReqDTO {

    @Getter
    public static class UpdatePreferenceDTO {
        private List<Long> categoryIds;
    }
}