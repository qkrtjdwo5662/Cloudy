package com.cloudy.domain.server.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CpuUsageDto {
    private Long serverId;  // serverId는 long 타입으로 보입니다.
    private Double cpuPercent;
    private Double memPercent;
}
