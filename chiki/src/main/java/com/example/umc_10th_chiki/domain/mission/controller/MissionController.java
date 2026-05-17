package com.example.umc_10th_chiki.domain.mission.controller;

import com.example.umc_10th_chiki.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th_chiki.domain.mission.dto.MissionResDTO;
import com.example.umc_10th_chiki.domain.mission.service.MissionQueryService;
import com.example.umc_10th_chiki.domain.mission.converter.MissionConverter;
import com.example.umc_10th_chiki.domain.mission.entity.MemberMission;
import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionQueryService missionQueryService;

    // [7주차 미션] 내가 진행 중인 미션 조회하기 (오프셋 기반)
    @GetMapping("/api/v1/missions/ongoing")
    public ApiResponse<MissionResDTO.MissionPageDTO> getOngoingMissions(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Page<MemberMission> missionPage = missionQueryService.getOngoingMissions(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPageDTO(missionPage));
    }

    //홈 화면 미션 조회
    @GetMapping("/users/{user_id}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissions(
            @PathVariable("user_id") Long userId,
            @RequestParam("regionId") Long regionId
    ) {
        return ApiResponse.onSuccess(null);
    }

    //미션 목록 조회 (진행중, 완료)
    @GetMapping("/users/{user_id}/member-missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMemberMissions(
            @PathVariable("user_id") Long userId,
            @RequestParam("status") List<String> statusList
    ) {
        return ApiResponse.onSuccess(null);
    }

    //미션 성공 누르기 (상태 변경)
    @PatchMapping("/member-missions/{my_mission_id}")
    public ApiResponse<MissionResDTO.UpdateStatusResultDTO> completeMission(
            @PathVariable("my_mission_id") Long myMissionId,
            @RequestBody @Valid MissionReqDTO.UpdateStatusDTO request
    ) {
        return ApiResponse.onSuccess(null);
    }
}