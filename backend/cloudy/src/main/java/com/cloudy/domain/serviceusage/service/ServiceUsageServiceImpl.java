package com.cloudy.domain.serviceusage.service;

import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUsageServiceImpl implements ServiceUsageService {

    @Override
    public ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId) {
        return null;
    }

}
