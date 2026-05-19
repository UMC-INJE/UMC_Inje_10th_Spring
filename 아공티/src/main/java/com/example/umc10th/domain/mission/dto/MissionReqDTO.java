package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MissionReqDTO {
    @Getter
    @Setter
    public static class ChallengeMissionListDTO {
        @NotNull(message = "사용자 ID(memberId)는 필수 입력 값입니다.")
        private Long memberId;
    }
}
