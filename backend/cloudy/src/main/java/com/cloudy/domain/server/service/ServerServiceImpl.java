package com.cloudy.domain.server.service;

import com.cloudy.domain.server.model.dto.request.ServerCreateRequest;
import com.cloudy.domain.server.model.dto.request.ServerUpdateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdCreateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdUpdateRequest;
import com.cloudy.domain.server.model.dto.response.MonitoringResponse;
import com.cloudy.domain.server.model.dto.response.ServerDetailResponse;
import com.cloudy.domain.server.model.dto.response.ServerResponse;
import com.cloudy.domain.server.model.dto.response.ThresholdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {


    @Override
    public ServerResponse createServer(ServerCreateRequest request, Long memberId) {
        return null;
    }

    @Override
    public ThresholdResponse createThreshold(ThresholdCreateRequest request, Long memberId) {
        return null;
    }

    @Override
    public ThresholdResponse updateThreshold(ThresholdUpdateRequest request, Long memberId) {
        return null;
    }

    @Override
    public List<ServerResponse> getServers(Long memberId) {
        return List.of();
    }

    @Override
    public ServerDetailResponse getServerDetail(Long serverId) {
        return null;
    }

    @Override
    public ServerResponse updateServer(ServerUpdateRequest request, Long memberId) {
        return null;
    }

    @Override
    public MonitoringResponse monitorServer(Long serverId, int duration) {
        return null;
    }
}
