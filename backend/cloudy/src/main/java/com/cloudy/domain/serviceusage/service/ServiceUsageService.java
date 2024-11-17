package com.cloudy.domain.serviceusage.service;

import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageCreateRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageGetServiceCostRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageGetServiceCostResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUseGetResponses;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface ServiceUsageService {
    ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId);
    ServiceUsageResponse createService(ServiceUsageCreateRequest request);
    ServiceUsageGetServiceCostResponse getServiceCost(ServiceUsageGetServiceCostRequest request);
    ServiceUseGetResponses getServicesUse(Long containerId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count);
}
