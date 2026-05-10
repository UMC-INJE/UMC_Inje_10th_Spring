package com.example.umc10th.domain.mission.Entity;

import com.example.umc10th.domain.store.Entity.Store;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deadline;

    @Column(name = "conditional_text") // 예약어 충돌 방지
    private String conditionalText;

    private Integer point;

    // 미션은 특정 가게에 속함 (주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}