package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(
                true,
                GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(),
                result
        );
    }
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T result) {
        return new ApiResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                result
        );
    }
}