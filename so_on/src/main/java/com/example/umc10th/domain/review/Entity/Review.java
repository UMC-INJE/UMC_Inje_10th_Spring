package com.example.umc10th.domain.review.Entity;

import com.example.umc10th.domain.store.Entity.Store;
import com.example.umc10th.domain.user.Entity.User;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Float star; // 별점

    // 임시로 String 처리 (실무에서는 이미지 테이블을 따로 빼거나 S3 URL 리스트로 관리)
    private String photoUrls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
