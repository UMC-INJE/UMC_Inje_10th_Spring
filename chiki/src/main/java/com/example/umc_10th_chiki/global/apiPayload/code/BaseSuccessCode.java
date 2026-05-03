package com.example.umc_10th_chiki.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}