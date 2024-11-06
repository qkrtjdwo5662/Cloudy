package com.cloudy.domain.container.service;

import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
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
}
