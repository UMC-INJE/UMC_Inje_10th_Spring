package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.dto.RegionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @GetMapping
    public ApiResponse<RegionResDTO.RegionListDTO> getRegions() {
        RegionResDTO.RegionDTO region = RegionResDTO.RegionDTO.builder()
                .regionId(1L)
                .regionName("서울특별시")
                .build();

        RegionResDTO.RegionListDTO response = RegionResDTO.RegionListDTO.builder()
                .regions(List.of(region))
                .build();

        return ApiResponse.onSuccess(response);
    }
}