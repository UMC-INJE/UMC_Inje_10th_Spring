package com.example.umc10th.domain.user.Entity.Mapping;

import com.example.umc10th.domain.food.Entity.Food;
import com.example.umc10th.domain.user.Entity.User;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
