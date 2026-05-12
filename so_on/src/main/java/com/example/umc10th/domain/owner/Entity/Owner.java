package com.example.umc10th.domain.owner.Entity;

import com.example.umc10th.global.common.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "business_num")
    private String businessNum; // 사업자등록번호
}