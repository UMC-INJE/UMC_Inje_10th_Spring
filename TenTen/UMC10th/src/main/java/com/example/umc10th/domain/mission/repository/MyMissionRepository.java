package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.MyMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMissionRepository extends JpaRepository<MyMission, Long> {

    // 내가 진행중/진행완료한 미션 조회
    Page<MyMission> findByUserUserIdAndStatus(
            Long userId,
            String status,
            Pageable pageable
    );
}