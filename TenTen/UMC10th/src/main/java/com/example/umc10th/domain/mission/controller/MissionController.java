package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class MissionController {
    @GetMapping("/home/missions")
    public ApiResponse<MissionResDTO.HomeMissionListDTO> getHomeMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Long regionId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        MissionResDTO.HomeMissionDTO mission = MissionResDTO.HomeMissionDTO.builder()
                .missionId(1L)
                .storeName("김밥천국")
                .missionContent("5000원 이상 주문하기")
                .reward("500P")
                .deadline("2026-05-10")
                .build();

        MissionResDTO.HomeMissionListDTO response = MissionResDTO.HomeMissionListDTO.builder()
                .missions(List.of(mission))
                .page(page)
                .size(size)
                .build();

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/my-mission")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String status,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        MissionResDTO.MyMissionDTO mission = MissionResDTO.MyMissionDTO.builder()
                .myMissionId(1L)
                .missionId(1L)
                .storeName("김밥천국")
                .missionContent("5000원 이상 주문하기")
                .status(status)
                .build();

        MissionResDTO.MyMissionListDTO response = MissionResDTO.MyMissionListDTO.builder()
                .myMissions(List.of(mission))
                .page(page)
                .size(size)
                .build();

        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/my-missions")
    public ApiResponse<MissionResDTO.ChallengeResultDTO> challengeMission(
            @RequestHeader("Authorization") String authorization,
            @RequestBody MissionReqDTO.ChallengeMissionDTO request
    ) {
        MissionResDTO.ChallengeResultDTO response = MissionResDTO.ChallengeResultDTO.builder()
                .myMissionId(1L)
                .missionId(request.getMissionId())
                .status("진행중")
                .message("미션 도전이 완료되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }

    @PatchMapping("/my-missions/{myMissionId}/success")
    public ApiResponse<MissionResDTO.MissionSuccessDTO> successMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long myMissionId
    ) {
        MissionResDTO.MissionSuccessDTO response = MissionResDTO.MissionSuccessDTO.builder()
                .myMissionId(myMissionId)
                .status("진행완료")
                .message("미션이 성공 처리되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/my-mission/{myMissionId}/review")
    public ApiResponse<MissionResDTO.ReviewPageDTO> getReviewPage(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long myMissionId
    ) {
        MissionResDTO.ReviewPageDTO response = MissionResDTO.ReviewPageDTO.builder()
                .myMissionId(myMissionId)
                .storeId(1L)
                .storeName("김밥천국")
                .missionContent("5000원 이상 주문하기")
                .build();

        return ApiResponse.onSuccess(response);
    }
}
