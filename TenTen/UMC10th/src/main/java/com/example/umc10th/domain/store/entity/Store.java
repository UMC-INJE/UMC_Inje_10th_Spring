package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeAddress;

    @Column(nullable = false)
    private String storePoint;

    @Builder.Default
    @Column(nullable = false)
    private Float storeRatingAvg = 0.0f;

    @Builder.Default
    @Column(nullable = false)
    private Integer storeReviewCount = 0;

    @Builder.Default
    @Column(nullable = false)
    private String status = "ACTIVE";
}
