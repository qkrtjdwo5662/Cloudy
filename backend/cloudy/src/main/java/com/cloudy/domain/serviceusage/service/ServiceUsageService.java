package com.cloudy.domain.serviceusage.service;

import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;

public interface ServiceUsageService {
    ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId);
}
