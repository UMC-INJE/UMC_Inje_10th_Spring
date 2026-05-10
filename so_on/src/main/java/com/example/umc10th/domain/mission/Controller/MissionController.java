package com.example.umc10th.domain.mission.Controller;

import com.example.umc10th.domain.mission.Service.MissionService;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "사용자 미션 목록 조회", description = "미션 화면에서 상태별 사용자 미션을 페이징 조회합니다.")
    @GetMapping("/{userId}/missions")
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissions(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam Integer page) {
        return ApiResponse.onSuccess(missionService.getMissions(userId, status, page));
    }

    @Operation(summary = "사용자 미션 상태 변경", description = "사용자 미션의 진행 상태를 변경합니다.")
    @PatchMapping("/{userId}/missions/{missionId}")
    public ApiResponse<String> updateMissionStatus(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody Map<String, String> body) {
        return ApiResponse.onSuccess(missionService.updateMissionStatus(userId, missionId, body.get("status")));
    }
}
/*
    //미션 상태 변경 02 (DTO를 사용하는 방법)
    public ApiResponse<String> updateMissionStatus(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.UpdateStatusDto request) {
        return ApiResponse.onSuccess(missionId + "번 미션 상태 변경 완료");
    }
    */
