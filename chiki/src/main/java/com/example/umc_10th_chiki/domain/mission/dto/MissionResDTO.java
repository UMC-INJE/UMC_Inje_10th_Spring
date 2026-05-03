package com.example.umc_10th_chiki.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        // 실제로는 미션 정보들이 담긴 List와 페이징 정보가 들어갑니다.
        private String placeholder;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusResultDTO {
        private Long memberMissionId;
        private String updatedStatus;
        private LocalDateTime updatedAt;
    }
}