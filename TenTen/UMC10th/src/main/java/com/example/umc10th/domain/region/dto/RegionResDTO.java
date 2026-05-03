package com.example.umc10th.domain.region.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class RegionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class RegionDTO {
        private Long regionId;
        private String regionName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class RegionListDTO {
        private List<RegionDTO> regions;
    }
}