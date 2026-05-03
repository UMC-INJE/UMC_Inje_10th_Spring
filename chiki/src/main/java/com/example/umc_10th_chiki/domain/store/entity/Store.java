
package com.example.umc_10th_chiki.domain.store.entity;



import jakarta.persistence.*;

import lombok.*;



@Entity

@Getter

@Builder

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor

public class Store {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

}

