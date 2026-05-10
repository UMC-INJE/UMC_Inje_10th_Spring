package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;

    public Page<Mission> getMissionListByAddress(Address address, Integer page) {

        String locationName = address.name();

        return missionRepository.findAllByLocationName(
                locationName,
                PageRequest.of(page, 10, Sort.by("createdAt").descending())
        );
    }
}
