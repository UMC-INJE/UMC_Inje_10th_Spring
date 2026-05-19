package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgressMissionRequestDto {

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Positive(message = "페이지 크기는 양수여야 합니다.")
        private Integer pageSize;

        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
        private Integer pageNumber;

        private String sort; // 정렬 기준 (예: "createdAt", "deadline" 등)
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusDto {
        @NotBlank(message = "미션 상태는 필수입니다.")
        private String status;
    }
}
