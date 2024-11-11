package com.cloudy.domain.container.service;

import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;
import com.cloudy.domain.container.repository.ContainerRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.server.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final ServerRepository serverRepository;
    private final ContainerRepository containerRepository;
    @Override
    public ContainerGetUsagesResponses getContainerUsages(ContainerGetUsagesRequest request) {
        return null;
    }

    @Override
    public ContainerGetMonthlyCostResponse getContainerMonthlyCosts(ContainerGetMonthlyCostRequest request) {
        return null;
    }

    @Override
    public ContainerGetDailyCostResponses getContainerDailyCosts(ContainerGetDailyCostRequest request) {
        return null;
    }

    @Override
    public ContainerGetServiceUsageResponses getContainerServiceUsages(ContainerGetServiceUsageRequest request) {
        return null;
    }

    @Override
    public ContainerUpdateNameResponse updateContainerName(ContainerUpdateNameRequest request) {
        return null;
    }

    @Override
    public void createContainer(ContainerCreateRequest containerCreateRequest, Long serverId) {
        Server server = serverRepository.findById(serverId).orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));;
        Container container = new Container(containerCreateRequest.getContainerName(), server);
        containerRepository.save(container);
    }
}
