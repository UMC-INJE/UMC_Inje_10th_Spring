package com.example.umc10th.domain.mission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Address {
    // MySQL의 location 테이블 name 컬럼에 실제로 들어있는 값들을 정의하세요.
    SEOUL("서울"),
    GIMHAE("김해"),
    BUSAN("부산");

    private final String description;
}