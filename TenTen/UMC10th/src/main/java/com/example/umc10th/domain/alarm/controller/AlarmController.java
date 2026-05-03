package com.example.umc10th.domain.alarm.controller;

import com.example.umc10th.domain.alarm.dto.AlarmReqDTO;
import com.example.umc10th.domain.alarm.dto.AlarmResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {

    @GetMapping
    public ApiResponse<AlarmResDTO.AlarmListDTO> getAlarms(
            @RequestHeader("Authorization") String authorization
    ) {
        AlarmResDTO.AlarmDTO alarm = AlarmResDTO.AlarmDTO.builder()
                .alarmId(1L)
                .alarmTitle("미션 알림")
                .alarmContent("진행 중인 미션이 있습니다.")
                .alarmRead(false)
                .build();

        AlarmResDTO.AlarmListDTO response = AlarmResDTO.AlarmListDTO.builder()
                .alarms(List.of(alarm))
                .build();

        return ApiResponse.onSuccess(response);
    }

    @PatchMapping("/settings")
    public ApiResponse<AlarmResDTO.AlarmSettingDTO> updateAlarmSetting(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AlarmReqDTO.UpdateAlarmDTO request
    ) {
        AlarmResDTO.AlarmSettingDTO response = AlarmResDTO.AlarmSettingDTO.builder()
                .alarmReview(request.getAlarmReview())
                .alarmQuestion(request.getAlarmQuestion())
                .alarmProgression(request.getAlarmProgression())
                .build();

        return ApiResponse.onSuccess(response);
    }
}
