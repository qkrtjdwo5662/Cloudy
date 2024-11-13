package com.cloudy.domain.serviceusage.service;

import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageCreateRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageGetServiceCostRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageGetServiceCostResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;

public interface ServiceUsageService {
    ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId);
    ServiceUsageResponse createService(ServiceUsageCreateRequest request);
    ServiceUsageGetServiceCostResponse getServiceCost(ServiceUsageGetServiceCostRequest request);
}
