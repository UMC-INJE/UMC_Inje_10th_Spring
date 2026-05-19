package com.example.umc_10th_chiki.domain.mission.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

public class MissionReqDTO {

    // [7주차 미션] 진행 중인 미션 조회용 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OngoingMissionDTO {
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusDTO {
        @NotBlank(message = "변경할 미션 상태값은 필수 입력 항목입니다.")
        private String status;
    }

    // [7주차 미션] 미션 생성 검증용 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionDTO {
        @NotNull(message = "마감일은 필수입니다.")
        @Future(message = "마감일은 과거일 수 없습니다.")
        private LocalDate deadline;

        @NotNull(message = "포인트는 필수입니다.")
        @Min(value = 1, message = "포인트는 1 이상이어야 합니다.")
        private Integer point;

        @NotBlank(message = "미션 조건은 비어있을 수 없습니다.")
        private String conditional;
    }
}