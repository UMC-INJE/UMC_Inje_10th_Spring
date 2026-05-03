package com.example.umc_10th_chiki.domain.mission.entity.mapping;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
