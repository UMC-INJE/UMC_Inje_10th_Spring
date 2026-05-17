package com.example.umc_10th_chiki.domain.mission.converter;

import com.example.umc_10th_chiki.domain.mission.dto.MissionResDTO;
import com.example.umc_10th_chiki.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPageDTO toMissionPageDTO(Page<MemberMission> missionPage) {
        List<MissionResDTO.MissionDTO> missionDTOList = missionPage.stream()
                .map(memberMission -> MissionResDTO.MissionDTO.builder()
                        .missionId(memberMission.getMission().getId())
                        .point(memberMission.getMission().getReward())
                        .conditional(memberMission.getMission().getMissionSpec())
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MissionPageDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }
}