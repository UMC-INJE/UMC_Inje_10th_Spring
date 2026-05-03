package com.example.umc_10th_chiki.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {
        @JsonProperty("user_name")
        private String userName;

        @JsonProperty("user_alias")
        private String userAlias;

        @JsonProperty("birth_date")
        private String birthDate;

        @JsonProperty("user_email")
        private String userEmail;

        @JsonProperty("region_id")
        private Long regionId;

        @JsonProperty("category_preferences")
        private List<CategoryPreferenceDTO> categoryPreferences;
    }

    @Getter
    public static class CategoryPreferenceDTO {
        @JsonProperty("category_id")
        private Long categoryId;
        private Integer score;
    }
}