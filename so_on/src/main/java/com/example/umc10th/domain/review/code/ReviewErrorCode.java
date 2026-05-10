package com.example.umc10th.domain.review.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    STORE_REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "해당 점포의 리뷰를 찾을 수 없습니다."),
    OWNER_STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_3", "사장님이 관리하는 점포를 찾을 수 없습니다."),
    INVALID_REVIEW_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 작성 요청 값이 올바르지 않습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW400_2", "이미 작성된 리뷰가 있습니다."),
    REPLY_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW400_3", "이미 답글이 등록된 리뷰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
