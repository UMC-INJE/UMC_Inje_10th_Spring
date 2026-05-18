package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;



    @Operation(
            summary = "홈 화면 미션 목록 조회 API",
            description = "현재 선택된 지역에서 도전 가능한 미션 목록을 조회합니다."
    )
    @GetMapping("/home/missions")
    public ApiResponse<MissionResDTO.HomeMissionListDTO> getHomeMissions(

            @Parameter(description = "Access Token", example = "Bearer accessToken")
            @RequestHeader(value = "Authorization", required = false) String authorization,

            @Parameter(description = "현재 선택된 지역 ID", example = "1")
            @RequestParam("region_id") Long regionId,

            @Parameter(description = "페이지 번호", example = "0")
            @RequestParam Integer page,

            @Parameter(description = "한 페이지에 가져올 데이터 수", example = "10")
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(
                missionService.getHomeMissions(regionId, page, size)
        );
    }


    @Operation(
            summary = "내 미션 목록 조회 API",
            description = "사용자가 진행중 또는 진행완료한 미션 목록을 조회합니다."
    )
    @GetMapping("/my-mission")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(

            @Parameter(description = "Access Token", example = "Bearer accessToken")
            @RequestHeader(value = "Authorization", required = false) String authorization,

            @Parameter(description = "미션 진행 상태", example = "진행중")
            @RequestParam String status,

            @Parameter(description = "페이지 번호", example = "0")
            @RequestParam Integer page,

            @Parameter(description = "한 페이지에 가져올 데이터 수", example = "10")
            @RequestParam Integer size
    ) {

        Long userId = 1L;

        return ApiResponse.onSuccess(
                missionService.getMyMissions(userId, status, page, size)
        );
    }
}