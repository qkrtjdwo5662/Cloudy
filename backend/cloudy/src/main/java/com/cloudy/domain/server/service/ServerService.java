package com.cloudy.domain.server.service;

import com.cloudy.domain.server.model.dto.request.*;
import com.cloudy.domain.server.model.dto.response.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public interface ServerService {
    ServerResponse createServer(ServerCreateRequest request, Long memberId);

    ThresholdResponse updateThreshold(ThresholdUpdateRequest request, Long memberId);

    List<ServerResponse> getServers(Long memberId);

    ServerDetailResponse getServerDetail(Long serverId);

    ServerResponse deleteServer(Long serverId, Long memberId);

    List<Long> monitorServer(Long serverId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count);

    List<ThresholdResponse> getThresholds(Long memberId);

    CpuUsage getCPUData(Long containerId) throws IOException;

    ServerMonthCostResponse monthServerCost(Long serverId, LocalDate date);

    ServerDailyCostResponse dailyServerCost(Long serverId, LocalDate date);

    Map<String, Double> weeklyServerCost(Long serverId, LocalDate date);

    CpuUsage getAllMemoryData(long serverId) throws IOException;
    void saveServerCPUUsage() throws IOException;
}
