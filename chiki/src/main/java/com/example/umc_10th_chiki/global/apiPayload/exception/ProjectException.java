package com.example.umc_10th_chiki.global.apiPayload.exception;

import com.example.umc_10th_chiki.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectException extends RuntimeException {

    // 우리가 만든 에러 규격(COMMON400, COMMON404 등)을 담아둘 필드
    private final BaseErrorCode code;
}