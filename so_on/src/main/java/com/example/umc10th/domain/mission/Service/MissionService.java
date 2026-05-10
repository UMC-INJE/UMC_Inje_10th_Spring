package com.example.umc10th.domain.mission.Service;

import com.example.umc10th.domain.mission.Entity.MemberMission;
import com.example.umc10th.domain.mission.Repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.code.MissionErrorCode;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final int MISSION_PAGE_SIZE = 10;

    private final MemberMissionRepository memberMissionRepository;

    public MissionResponseDTO.MissionListDto getMissions(Long userId, String status, Integer page) {
        MissionStatus missionStatus = parseStatus(status);
        int pageNumber = page == null || page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, MISSION_PAGE_SIZE);

        Page<MemberMission> missions = memberMissionRepository.findUserMissions(userId, missionStatus, pageable);

        List<MissionResponseDTO.MissionDetailDto> missionList = missions.getContent().stream()
                .map(this::toMissionDetailDto)
                .toList();

        return MissionResponseDTO.MissionListDto.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .currentPage(missions.getNumber() + 1)
                .isLast(missions.isLast())
                .build();
    }

    @Transactional
    public String updateMissionStatus(Long userId, Long missionId, String status) {
        MissionStatus missionStatus = parseStatus(status);

        MemberMission memberMission = memberMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        memberMission.updateStatus(missionStatus);
        return missionId + "번 미션이 " + missionStatus.name() + " 상태로 변경되었습니다.";
    }

    private MissionStatus parseStatus(String status) {
        try {
            return MissionStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException exception) {
            throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
        }
    }

    private MissionResponseDTO.MissionDetailDto toMissionDetailDto(MemberMission memberMission) {
        return MissionResponseDTO.MissionDetailDto.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getPoint())
                .missionContent(memberMission.getMission().getConditionalText())
                .deadline(memberMission.getMission().getDeadline() == null ? null : memberMission.getMission().getDeadline().toString())
                .status(memberMission.getStatus().name())
                .build();
    }
}
