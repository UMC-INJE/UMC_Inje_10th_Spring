package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResDTO.MissionPreViewDTO.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getRewardPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {
        List<MissionResDTO.MissionPreViewDTO> missionPreViewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionPreViewDTO).collect(Collectors.toList());

        return MissionResDTO.MissionPreViewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

}