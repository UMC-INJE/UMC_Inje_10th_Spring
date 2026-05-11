package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(nullable = false)
    private String userLoginId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userGender;

    @Column(nullable = false)
    private LocalDate userBirthdate;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userNickname;

    @Builder.Default
    @Column(nullable = false)
    private Integer userPoint = 0;

    @Builder.Default
    @Column(nullable = false)
    private String status = "ACTIVE";
}