package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionDTO {
        private Long missionId;
        private String storeName;
        private String missionContent;
        private String reward;
        private String deadline;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionListDTO {
        private List<HomeMissionDTO> missions;
        private Integer page;
        private Integer size;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionDTO {
        private Long myMissionId;
        private Long missionId;
        private String storeName;
        private String missionContent;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListDTO {
        private List<MyMissionDTO> myMissions;
        private Integer page;
        private Integer size;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChallengeResultDTO {
        private Long myMissionId;
        private Long missionId;
        private String status;
        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionSuccessDTO {
        private Long myMissionId;
        private String status;
        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewPageDTO {
        private Long myMissionId;
        private Long storeId;
        private String storeName;
        private String missionContent;
    }
}
