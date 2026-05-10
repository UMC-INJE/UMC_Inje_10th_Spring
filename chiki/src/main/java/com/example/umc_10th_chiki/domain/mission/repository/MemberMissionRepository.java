package com.example.umc_10th_chiki.domain.mission.repository;

import com.example.umc_10th_chiki.domain.mission.entity.MemberMission;
import com.example.umc_10th_chiki.domain.mission.entity.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    /**
     * [6주차 미션] 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
     * 특정 회원의 미션 기록을 상태별(status)로 조회.
     */
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findMyMissions(@Param("memberId") Long memberId, @Param("status") MissionStatus status, Pageable pageable);
}