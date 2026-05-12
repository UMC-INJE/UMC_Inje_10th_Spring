package com.example.umc10th.domain.mission.Repository;

import com.example.umc10th.domain.mission.Entity.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
            value = """
                    select mm
                    from MemberMission mm
                    join fetch mm.mission m
                    join fetch m.store s
                    where mm.user.id = :userId
                      and mm.status = :status
                    order by mm.id desc
                    """,
            countQuery = """
                    select count(mm)
                    from MemberMission mm
                    where mm.user.id = :userId
                      and mm.status = :status
                    """
    )
    Page<MemberMission> findUserMissions(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    long countByUserIdAndStatus(Long userId, MissionStatus status);

    Optional<MemberMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
