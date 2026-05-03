package com.example.umc_10th_chiki.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.umc_10th_chiki.domain.mission.entity.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
}
