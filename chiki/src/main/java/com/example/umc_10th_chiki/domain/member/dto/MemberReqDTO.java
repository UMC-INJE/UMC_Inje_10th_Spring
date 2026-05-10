package com.example.umc_10th_chiki.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @JsonProperty("user_name") String userName,
            @JsonProperty("user_alias") String userAlias,
            @JsonProperty("birth_date") String birthDate,
            @JsonProperty("user_email") String userEmail,
            @JsonProperty("user_address") String userAddress,
            @JsonProperty("user_gender") Integer userGender,
            @JsonProperty("user_phone") String userPhone,
            @JsonProperty("region_id") Long regionId,
            @JsonProperty("notify_setting_id") Long notifySettingId,
            @JsonProperty("category_preferences") List<CategoryPreferenceDTO> categoryPreferences
    ) {}

    public record CategoryPreferenceDTO(
            @JsonProperty("category_id") Long categoryId,
            Integer score
    ) {}
}