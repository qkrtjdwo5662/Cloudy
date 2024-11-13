package com.cloudy.domain.server.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuUsage {

    private double cpuPercent;
    private double memUsage;
    private double memLimit;
}
