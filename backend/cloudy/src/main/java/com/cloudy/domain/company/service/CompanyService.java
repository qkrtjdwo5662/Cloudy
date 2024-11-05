package com.cloudy.domain.company.service;

import com.cloudy.domain.company.model.dto.request.CompanyCreateRequest;
import com.cloudy.domain.company.model.dto.request.CompanyDuplicateCheckRequest;
import com.cloudy.domain.company.model.dto.request.ContainerUpdateRequest;
import com.cloudy.domain.company.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.company.model.dto.response.*;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(CompanyCreateRequest request);

    CompanyDuplicateCheckResponse checkDuplicate(CompanyDuplicateCheckRequest request);

    List<InstanceResponse> getInstanceList(String cloudType, String search);

    ContainerResponse updateContainer(ContainerUpdateRequest request);

    ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request);
}
