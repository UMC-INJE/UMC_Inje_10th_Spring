package com.example.umc_10th_chiki.domain.mission.dto;

import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class UpdateStatusDTO {
        private String status; // COMPLETE 등
    }
}