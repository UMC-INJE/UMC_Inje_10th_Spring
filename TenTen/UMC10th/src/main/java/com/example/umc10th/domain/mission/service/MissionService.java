package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.MyMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.MyMissionRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MyMissionRepository myMissionRepository;

    // 홈 화면 쿼리
    public MissionResDTO.HomeMissionListDTO getHomeMissions(
            Long regionId,
            Integer page,
            Integer size
    ) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(
                regionId,
                PageRequest.of(page, size)
        );

        return MissionConverter.toHomeMissionListDTO(missions);
    }

    // 내가 진행중/진행완료한 미션 조회
    public MissionResDTO.MyMissionListDTO getMyMissions(
            Long userId,
            String status,
            Integer page,
            Integer size
    ) {
        Page<MyMission> myMissions = myMissionRepository.findByUserUserIdAndStatus(
                userId,
                status,
                PageRequest.of(page, size)
        );

        return MissionConverter.toMyMissionListDTO(myMissions);
    }
}