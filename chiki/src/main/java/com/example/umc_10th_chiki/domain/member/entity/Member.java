package com.example.umc_10th_chiki.domain.member.entity;

import com.example.umc_10th_chiki.domain.store.entity.Region;
import com.example.umc_10th_chiki.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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

    @Column(name = "birth_date", length = 20)
    private String birthDate;

    @Column(name = "user_address", length = 100)
    private String address;

    @Column(name = "user_email", nullable = false, length = 100)
    private String email;

    @Column(name = "user_gender")
    private Integer gender;

    @Column(name = "user_phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;

    @Column(name = "notify_setting_id", nullable = false)
    private Long notifySettingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}