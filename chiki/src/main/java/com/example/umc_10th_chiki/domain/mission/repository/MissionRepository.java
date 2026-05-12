package com.example.umc_10th_chiki.domain.mission.repository;

import com.example.umc_10th_chiki.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * [6주차 미션] 홈 화면 쿼리
     * 현재 선택된 지역에서 도전이 가능한 미션 목록을 조회.
     * 지역(Region) 정보를 필터링하기 위해 Store와 Join이 필요하다.
     */
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findMissionsByRegionId(@Param("regionId") Long regionId, Pageable pageable);
}