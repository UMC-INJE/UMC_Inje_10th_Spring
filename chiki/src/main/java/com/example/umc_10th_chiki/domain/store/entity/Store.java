package com.example.umc_10th_chiki.domain.store.entity;

import com.example.umc_10th_chiki.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, name = "store_name", length = 50)
    private String storeName;

    @Column(nullable = false, name = "store_address", length = 200)
    private String storeAddress;

    @Column(name = "store_level")
    private Float storeLevel;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "region_id")
    private Long region_id;
}