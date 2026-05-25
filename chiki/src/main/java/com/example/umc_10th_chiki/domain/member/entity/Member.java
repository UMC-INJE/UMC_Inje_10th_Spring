package com.example.umc_10th_chiki.domain.member.entity;

import com.example.umc_10th_chiki.domain.store.entity.Region;
import com.example.umc_10th_chiki.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_alias", nullable = false, length = 50)
    private String alias;

    @Column(name = "user_email", nullable = false, length = 50)
    private String email;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_gender")
    private Integer gender;

    @Column(name = "user_phone")
    private String phone;

    @Column(nullable = false)
    private String password;

    private String birthDate;

    private String status;

    private Long notifySettingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}