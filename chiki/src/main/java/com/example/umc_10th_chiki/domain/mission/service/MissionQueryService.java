package com.example.umc_10th_chiki.domain.mission.service;

import com.example.umc_10th_chiki.domain.mission.entity.MemberMission;
import com.example.umc_10th_chiki.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMission> getOngoingMissions(Long memberId, Integer page) {
        // JPA 페이지는 0부터 시작하므로 page - 1 처리
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "id"));
        return memberMissionRepository.findAllByMemberId(memberId, pageRequest);

    }
}