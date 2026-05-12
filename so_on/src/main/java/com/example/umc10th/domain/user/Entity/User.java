package com.example.umc10th.domain.user.Entity;

import com.example.umc10th.domain.mission.Entity.MemberMission;
import com.example.umc10th.domain.review.Entity.Review;
import com.example.umc10th.domain.user.Entity.Mapping.MemberFood;
import com.example.umc10th.domain.user.Entity.Mapping.MemberTerm;
import com.example.umc10th.domain.user.enums.Address;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users") // 클래스명은 User로 쓰되, DB 테이블 에러 방지를 위해 users로 생성
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // ERD의 user_ID 컬럼과 매핑
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @Column(name = "social_uid", length = 255)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "profile_url", columnDefinition = "TEXT")
    private String profileUrl;

    // ==========================================
    // 관계 매핑 (연관관계의 비주인들)
    // ==========================================

    // 1. 사용자가 선택한 선호 음식 리스트 (1:N)
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MemberFood> userFoodList = new ArrayList<>();

    // 2. 사용자가 동의한 약관 리스트 (1:N)
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MemberTerm> userTermList = new ArrayList<>();

    // 3. 사용자가 도전 중인 미션 리스트 (1:N)
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MemberMission> userMissionList = new ArrayList<>();

    // 4. 사용자가 작성한 리뷰 리스트 (1:N)
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
