package com.cloudy.domain.server.service;

import com.cloudy.domain.server.model.dto.request.ServerCreateRequest;
import com.cloudy.domain.server.model.dto.request.ServerUpdateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdCreateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdUpdateRequest;
import com.cloudy.domain.server.model.dto.response.MonitoringResponse;
import com.cloudy.domain.server.model.dto.response.ServerDetailResponse;
import com.cloudy.domain.server.model.dto.response.ServerResponse;
import com.cloudy.domain.server.model.dto.response.ThresholdResponse;

import java.util.List;

public interface ServerService {
    ServerResponse createServer(ServerCreateRequest request, Long memberId);

    ThresholdResponse updateThreshold(ThresholdUpdateRequest request, Long memberId);

    List<ServerResponse> getServers(Long memberId);

    ServerDetailResponse getServerDetail(Long serverId);

    ServerResponse deleteServer(Long serverId, Long memberId);

    MonitoringResponse monitorServer(Long serverId, int duration);

    List<ThresholdResponse> getThresholds(Long memberId);
}
