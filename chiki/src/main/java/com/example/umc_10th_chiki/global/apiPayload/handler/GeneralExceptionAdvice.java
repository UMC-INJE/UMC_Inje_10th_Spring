package com.example.umc_10th_chiki.global.apiPayload.handler;

import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import com.example.umc_10th_chiki.global.apiPayload.code.BaseErrorCode;
import com.example.umc_10th_chiki.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_10th_chiki.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // [7주차 미션] @Valid 검증 실패 시 스프링 부트 프레임워크가 던지는 MethodArgumentNotValidException 예외를 정의
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        // DTO 내부 필드에 선언된 @NotBlank(message = "...")의 커스텀 에러 메시지들을 바인딩
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // 프로젝트 표준 에러 규격인 GeneralErrorCode.BAD_REQUEST 객체를 매핑
        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, errors));
    }

    // 프로젝트에서 발생한 커스텀 예외 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberException(
            ProjectException e
    ) {
        BaseErrorCode errorCode = e.getCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    // 그 외의 정의되지 않은 모든 최상위 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }
}