package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
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
    public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(MemberMission memberMission) {
        return MissionResDTO.MissionPreViewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getRewardPoint())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    // Page<MemberMission> 전체를 DTO 리스트로 변환
    public static MissionResDTO.MissionPreViewListDTO toMemberMissionPreViewListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResDTO.MissionPreViewDTO> missionPreViewDTOList = memberMissionPage.stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreViewListDTO.builder()
                .isLast(memberMissionPage.isLast())
                .isFirst(memberMissionPage.isFirst())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

}