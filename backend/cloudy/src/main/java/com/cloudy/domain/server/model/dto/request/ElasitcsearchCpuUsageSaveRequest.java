package com.cloudy.domain.server.model.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ElasitcsearchCpuUsageSaveRequest {

    private long serverId;
    private double cpuPercent;
    private double memPercent;
}
