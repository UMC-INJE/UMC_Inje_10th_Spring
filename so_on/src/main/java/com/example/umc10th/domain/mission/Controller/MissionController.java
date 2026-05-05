package com.example.umc10th.domain.mission.Controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MissionController {

    @GetMapping("/{userId}/missions")
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissions(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam Integer page) {
        return ApiResponse.onSuccess(MissionResponseDTO.MissionListDto.builder()
                .missionList(new ArrayList<>()) // 빈 리스트 응답
                .totalPage(1)
                .build());
    }

    @PatchMapping("/{userId}/missions/{missionId}")
    public ApiResponse<String> updateMissionStatus(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody Map<String, String> body) {
        return ApiResponse.onSuccess(missionId + "번 미션이 " + body.get("status") + " 되었습니다.");
    }
}