package com.example.umc10th.domain.store.Entity;

import com.example.umc10th.domain.owner.Entity.Owner;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "detail_address")
    private String detailAddress;

    // 가게는 하나의 지역에 속함 (주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // 가게는 한 명의 사장님이 소유함 (주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
