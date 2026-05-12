package com.example.umc10th.domain.mission.Entity;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.Entity.User;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 미션 상태 (도전중, 완료) Enum 처리를 권장합니다.
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    // 어떤 사용자의 미션인지 (주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 어떤 미션인지 (주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void updateStatus(MissionStatus status) {
        this.status = status;
    }
}
