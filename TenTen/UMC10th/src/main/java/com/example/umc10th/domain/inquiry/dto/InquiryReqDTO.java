package com.example.umc10th.domain.inquiry.dto;


import lombok.Getter;

public class InquiryReqDTO {

    @Getter
    public static class CreateInquiryDTO {
        private String inquiryTitle;
        private String inquiryType;
        private String inquiryContent;
    }
}