package com.cloudy.domain.alarm.model.dto.response;

import com.cloudy.domain.alarm.model.Alarm;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AlarmResponse {

    private Long alarmId;
    private String serverName;
    private String title;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;

    public static AlarmResponse fromEntity(Alarm alarm) {
        return AlarmResponse.builder()
                .alarmId(alarm.getAlramId())
                .serverName(alarm.getServerName())
                .title(alarm.getTitle())
                .content(alarm.getContent())
                .isRead((alarm.getIsRead()))
                .createdAt(alarm.getCreatedAt())
                .build();
    }
}