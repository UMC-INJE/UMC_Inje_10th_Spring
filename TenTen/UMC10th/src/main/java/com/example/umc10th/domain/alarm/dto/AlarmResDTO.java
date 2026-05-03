package com.example.umc10th.domain.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class AlarmResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AlarmDTO {
        private Long alarmId;
        private String alarmTitle;
        private String alarmContent;
        private Boolean alarmRead;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AlarmListDTO {
        private List<AlarmDTO> alarms;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AlarmSettingDTO {
        private Boolean alarmReview;
        private Boolean alarmQuestion;
        private Boolean alarmProgression;
    }
}
