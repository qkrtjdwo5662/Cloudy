package com.cloudy.domain.server.service;

import com.cloudy.domain.server.model.dto.request.ServerCreateRequest;
import com.cloudy.domain.server.model.dto.request.ServerUpdateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdCreateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdUpdateRequest;
import com.cloudy.domain.server.model.dto.response.*;

import java.io.IOException;
import java.util.List;

public interface ServerService {
    ServerResponse createServer(ServerCreateRequest request, Long memberId);

    ThresholdResponse updateThreshold(ThresholdUpdateRequest request, Long memberId);

    List<ServerResponse> getServers(Long memberId);

    ServerDetailResponse getServerDetail(Long serverId);

    ServerResponse deleteServer(Long serverId, Long memberId);

    MonitoringResponse monitorServer(Long serverId, int duration);

    List<ThresholdResponse> getThresholds(Long memberId);

    CpuUsage getCPUData(Long containerId) throws IOException;

}
