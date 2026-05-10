package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResponseDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.MyMissionDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getRewardPoint())
                .status(memberMission.getStatus().toString())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberMissionResponseDTO.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> missionPage) {
        List<MemberMissionResponseDTO.MyMissionDTO> myMissionDTOList = missionPage.stream()
                .map(MemberMissionConverter::toMyMissionDTO).collect(Collectors.toList());

        return MemberMissionResponseDTO.MyMissionListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(myMissionDTOList.size())
                .missionList(myMissionDTOList)
                .build();
    }
}