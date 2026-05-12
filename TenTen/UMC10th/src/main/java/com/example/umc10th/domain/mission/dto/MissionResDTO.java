package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionDTO {

        @JsonProperty("mission_id")
        private Long missionId;

        @JsonProperty("store_name")
        private String storeName;

        @JsonProperty("mission_reward")
        private String missionReward;

        @JsonProperty("mission_content")
        private String missionContent;

        private String deadline;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionListDTO {

        @JsonProperty("mission_list")
        private List<HomeMissionDTO> missionList;

        private Integer page;
        private Integer size;

        @JsonProperty("has_next")
        private Boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionDTO {

        @JsonProperty("my_mission_id")
        private Long myMissionId;

        @JsonProperty("mission_id")
        private Long missionId;

        @JsonProperty("store_name")
        private String storeName;

        @JsonProperty("mission_reward")
        private String missionReward;

        @JsonProperty("mission_content")
        private String missionContent;

        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListDTO {

        @JsonProperty("my_mission_list")
        private List<MyMissionDTO> myMissionList;

        private Integer page;
        private Integer size;

        @JsonProperty("has_next")
        private Boolean hasNext;
    }
}