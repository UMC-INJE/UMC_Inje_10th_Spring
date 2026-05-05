package com.example.umc_10th_chiki.domain.mission.controller;

import com.example.umc_10th_chiki.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th_chiki.domain.mission.dto.MissionResDTO;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {

    // private final MissionService missionService;

    // 1. 홈 화면 미션 조회
    // 치웅님의 메모대로, 향후 @PathVariable("user_id") 대신 JWT를 활용한 @AuthUser 등의 방식으로 전환하기 좋은 포인트입니다!
    @GetMapping("/users/{user_id}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissions(
            @PathVariable("user_id") Long userId,
            @RequestParam("regionId") Long regionId
    ) {
        return ApiResponse.onSuccess(null);
    }

    // 2. 미션 목록 조회 (진행중, 완료)
    // 쉼표(,)로 들어온 status 값들을 Spring이 알아서 List<String> 형태로 분리해서 받아줍니다!
    @GetMapping("/users/{user_id}/member-missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMemberMissions(
            @PathVariable("user_id") Long userId,
            @RequestParam("status") List<String> statusList
    ) {
        return ApiResponse.onSuccess(null);
    }

    // 3. 미션 성공 누르기 (상태 변경)
    @PatchMapping("/member-missions/{my_mission_id}")
    public ApiResponse<MissionResDTO.UpdateStatusResultDTO> completeMission(
            @PathVariable("my_mission_id") Long myMissionId,
            @RequestBody MissionReqDTO.UpdateStatusDTO request
    ) {
        return ApiResponse.onSuccess(null);
    }
}