package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.member.converter.MemberMissionConverter;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MemberMissionResponseDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MissionController {
    private final MemberService memberService;
    private final MissionService missionService;

        @GetMapping("/{memberId}/missions")
        public ApiResponse<MemberMissionResponseDTO.MyMissionListDTO> getMyMissions(
                @PathVariable Long memberId,
                @RequestParam(name = "status") MissionStatus status,
                @RequestParam(name = "page") Integer page
        ) {
            Page<MemberMission> missionPage = memberService.getMyMissionList(memberId, status, page);

            return ApiResponse.onSuccess(MemberMissionConverter.toMyMissionListDTO(missionPage));
        }

    @GetMapping("")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionsByAddress(
            @RequestParam(name = "address") Address address,
            @RequestParam(name = "page") Integer page
    ) {
        Page<Mission> missionPage = missionService.getMissionListByAddress(address, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreViewListDTO(missionPage));
    }
    }

