package com.cloudy.domain.serviceusage.service;

import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.container.repository.ContainerRepository;
import com.cloudy.domain.serviceusage.model.ServiceUsage;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageCreateRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageGetServiceCostRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageGetServiceCostResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import com.cloudy.domain.serviceusage.repository.ServiceUsageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUsageServiceImpl implements ServiceUsageService {
    private final ServiceUsageRepository serviceUsageRepository;
    private final ContainerRepository containerRepository;
    @Override
    public ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId) {
        return null;
    }

    @Override
    public ServiceUsageResponse createService(ServiceUsageCreateRequest request) {
        Container container = containerRepository.findById(request.getContainerID()).orElseThrow(()-> new NotFoundException("not found"));
        ServiceUsage serviceUsage = new ServiceUsage(request.getServiceType(), request.getServiceName(), request.getServiceCost(), container);
        serviceUsageRepository.save(serviceUsage);

        return null;
    }

    @Override
    public ServiceUsageGetServiceCostResponse getServiceCost(ServiceUsageGetServiceCostRequest request) {
        ServiceUsage serviceUsage = serviceUsageRepository.findServiceUsageByServiceNameAndServiceType(request.getServiceName(), request.getServiceType());
        return ServiceUsageGetServiceCostResponse.from(serviceUsage);
    }


}
