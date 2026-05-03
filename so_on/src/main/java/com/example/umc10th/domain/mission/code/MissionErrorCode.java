package com.example.umc10th.domain.mission.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "사용자의 미션 정보를 찾을 수 없습니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_1", "미션 상태 값이 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
