package com.example.umc10th.domain.inquiry.controller;

import com.example.umc10th.domain.inquiry.dto.InquiryReqDTO;
import com.example.umc10th.domain.inquiry.dto.InquiryResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    @PostMapping
    public ApiResponse<InquiryResDTO.InquiryResultDTO> createInquiry(
            @RequestHeader("Authorization") String authorization,
            @RequestBody InquiryReqDTO.CreateInquiryDTO request
    ) {
        InquiryResDTO.InquiryResultDTO response = InquiryResDTO.InquiryResultDTO.builder()
                .inquiryId(1L)
                .inquiryTitle(request.getInquiryTitle())
                .inquiryType(request.getInquiryType())
                .inquiryContent(request.getInquiryContent())
                .message("문의가 등록되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }
}