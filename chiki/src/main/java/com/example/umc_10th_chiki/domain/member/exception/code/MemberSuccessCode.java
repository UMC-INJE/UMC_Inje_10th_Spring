package com.example.umc_10th_chiki.domain.member.exception.code;

import com.example.umc_10th_chiki.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String message;
    private final String code;
}
