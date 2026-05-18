package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyMissionPreviewDTO {

        @JsonProperty("my_mission_id")
        Long myMissionId;

        @JsonProperty("mission_title")
        String missionTitle;

        @JsonProperty("mission_reward")
        String missionReward;

        @JsonProperty("mission_status")
        String missionStatus;
    }

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
    @NoArgsConstructor
    public static class MyMissionPageDTO {

        @JsonProperty("mission_list")
        List<MyMissionPreviewDTO> missionList;

        @JsonProperty("list_size")
        Integer listSize;

        @JsonProperty("total_page")
        Integer totalPage;

        @JsonProperty("total_elements")
        Long totalElements;

        @JsonProperty("is_first")
        Boolean isFirst;

        @JsonProperty("is_last")
        Boolean isLast;
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