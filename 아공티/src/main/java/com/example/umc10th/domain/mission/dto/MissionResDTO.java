package com.example.umc10th.domain.mission.dto;

import lombok.*;
import java.time.LocalDateTime; // LocalDate에서 LocalDateTime으로 변경 (엔티티와 일치)
import java.util.List;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        String storeName;
        String missionSpec;
        Integer reward;
        LocalDateTime deadline;
    }
}