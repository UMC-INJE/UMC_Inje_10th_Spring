package com.example.umc10th.domain.term.Entity;

import com.example.umc10th.domain.term.Enums.TermName;
import com.example.umc10th.global.common.BaseEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermName name; // AGE, SERVICE, PRIVACY 등
}
