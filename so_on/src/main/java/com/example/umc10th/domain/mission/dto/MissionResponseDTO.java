package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


// static class를 사용한 구현부
public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDto {
        List<MissionDetailDto> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Integer currentPage;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDto {
        Long missionId;
        String storeName;
        Integer reward;
        String missionContent;
        String deadline;
        String status;
    }
}
/*
// ⭐️ record 사용한 구현 -> 코드 길이가 매우 간소화 됨. but, 다른 코드에 @Builder 의존 문제가 남아있어 추후 한번에 수정 예정
public class MissionResponseDTO {
    public record MissionListDto(
            List<MissionDetailDto> missionList,
            Integer totalPage
    ) {}

    public record MissionDetailDto(
            Long missionId,
            String storeName,
            Integer reward,
            String deadline,
            String status
    ) {}
}
*/
