package com.example.umc10th.domain.mission.Repository;

import com.example.umc10th.domain.mission.Entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
