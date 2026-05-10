package com.example.umc10th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus(); //*브라우저나 앱이 알아먹을 수 있는 HTTP 상태 코드

    String getCode(); //서버만의 고유 에러/성공 코드

    String getMessage(); //용자나 개발자가 읽을 수 있는 친절한 설명
}