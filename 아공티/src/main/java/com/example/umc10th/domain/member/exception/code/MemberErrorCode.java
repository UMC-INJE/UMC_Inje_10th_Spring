package com.example.umc10th.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    // Member 관련 에러 (피드백에서 언급된 MEMBER_NOT_FOUND 추가)
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4002", "이미 존재하는 사용자입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}