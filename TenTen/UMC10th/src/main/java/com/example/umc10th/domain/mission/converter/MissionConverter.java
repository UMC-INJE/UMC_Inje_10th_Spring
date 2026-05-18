package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.MyMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        return MissionResDTO.HomeMissionDTO.builder()
                .missionId(mission.getMissionId())
                .storeName(mission.getStore().getStoreName())
                .missionReward(mission.getMissionReward())
                .missionContent(mission.getMissionContent())
                .deadline(String.valueOf(mission.getDeadline()))
                .build();
    }

    public static MissionResDTO.HomeMissionListDTO toHomeMissionListDTO(Page<Mission> missions) {
        List<MissionResDTO.HomeMissionDTO> missionList = missions.stream()
                .map(MissionConverter::toHomeMissionDTO)
                .toList();

        return MissionResDTO.HomeMissionListDTO.builder()
                .missionList(missionList)
                .page(missions.getNumber())
                .size(missions.getSize())
                .hasNext(missions.hasNext())
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(MyMission myMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .myMissionId(myMission.getMyMissionId())
                .missionId(myMission.getMission().getMissionId())
                .storeName(myMission.getMission().getStore().getStoreName())
                .missionReward(myMission.getMission().getMissionReward())
                .missionContent(myMission.getMission().getMissionContent())
                .status(myMission.getStatus())
                .build();
    }
    public static MissionResDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(MyMission myMission){

        return MissionResDTO.MyMissionPreviewDTO.builder()
                .myMissionId(myMission.getMyMissionId())
                .missionTitle(myMission.getMission().getMissionTitle())
                .missionReward(myMission.getMission().getMissionReward())
                .missionStatus(myMission.getStatus())
                .build();
    }
    public static MissionResDTO.MyMissionPageDTO toMyMissionPageDTO(Page<MyMission> myMissionPage){

        List<MissionResDTO.MyMissionPreviewDTO> missionList =
                myMissionPage.stream()
                        .map(MissionConverter::toMyMissionPreviewDTO)
                        .toList();

        return MissionResDTO.MyMissionPageDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(myMissionPage.getTotalPages())
                .totalElements(myMissionPage.getTotalElements())
                .isFirst(myMissionPage.isFirst())
                .isLast(myMissionPage.isLast())
                .build();
    }
    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<MyMission> myMissions) {
        List<MissionResDTO.MyMissionDTO> myMissionList = myMissions.stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionResDTO.MyMissionListDTO.builder()
                .myMissionList(myMissionList)
                .page(myMissions.getNumber())
                .size(myMissions.getSize())
                .hasNext(myMissions.hasNext())
                .build();
    }
}