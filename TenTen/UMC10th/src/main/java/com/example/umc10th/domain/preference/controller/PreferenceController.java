package com.example.umc10th.domain.preference.controller;

import com.example.umc10th.domain.preference.dto.PreferenceReqDTO;
import com.example.umc10th.domain.preference.dto.PreferenceResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    @PatchMapping
    public ApiResponse<PreferenceResDTO.PreferenceDTO> updatePreference(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PreferenceReqDTO.UpdatePreferenceDTO request
    ) {
        PreferenceResDTO.PreferenceDTO response = PreferenceResDTO.PreferenceDTO.builder()
                .categoryIds(request.getCategoryIds())
                .message("선호 카테고리가 수정되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }
}