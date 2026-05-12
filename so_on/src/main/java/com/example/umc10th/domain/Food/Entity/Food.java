package com.example.umc10th.domain.food.Entity;

import com.example.umc10th.domain.Food.Enums.FoodName;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_name", nullable = false)
    private FoodName name; // KOREAN, DESSERT 등
}
