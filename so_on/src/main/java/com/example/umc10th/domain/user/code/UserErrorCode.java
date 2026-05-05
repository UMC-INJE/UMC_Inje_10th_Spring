package com.example.umc10th.domain.user.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "USER400_1", "이미 사용 중인 이메일입니다."),
    INVALID_SIGNUP_REQUEST(HttpStatus.BAD_REQUEST, "USER400_2", "회원가입 요청 값이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
