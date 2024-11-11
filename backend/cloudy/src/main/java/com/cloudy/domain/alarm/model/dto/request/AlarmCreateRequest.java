package com.cloudy.domain.alarm.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmCreateRequest {

    private String serverName;
    private String title;
    private String content;
}