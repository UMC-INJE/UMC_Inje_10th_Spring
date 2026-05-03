package com.example.umc_10th_chiki.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}