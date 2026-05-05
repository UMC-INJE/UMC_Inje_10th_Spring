package com.example.umc10th.domain.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class InquiryResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class InquiryResultDTO {
        private Long inquiryId;
        private String inquiryTitle;
        private String inquiryType;
        private String inquiryContent;
        private String message;
    }
}