package com.example.umc10th.domain.alarm.dto;

import lombok.Getter;

public class AlarmReqDTO {

    @Getter
    public static class UpdateAlarmDTO {
        private Boolean alarmReview;
        private Boolean alarmQuestion;
        private Boolean alarmProgression;
    }
}
