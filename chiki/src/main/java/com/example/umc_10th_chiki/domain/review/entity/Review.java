
package com.example.umc_10th_chiki.domain.review.entity;



import jakarta.persistence.*;

import lombok.*;



@Entity

@Getter

@Builder

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor

public class Review {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

}

