package com.cloudy.domain.company.service;

import com.cloudy.domain.company.model.dto.request.CompanyCreateRequest;
import com.cloudy.domain.company.model.dto.request.CompanyDuplicateCheckRequest;
import com.cloudy.domain.company.model.dto.request.ContainerUpdateRequest;
import com.cloudy.domain.company.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.company.model.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    @Override
    public CompanyResponse createCompany(CompanyCreateRequest request) {
        return null;
    }

    @Override
    public CompanyDuplicateCheckResponse checkDuplicate(CompanyDuplicateCheckRequest request) {
        return null;
    }

    @Override
    public List<InstanceResponse> getInstanceList(String cloudType, String search) {
        return List.of();
    }

    @Override
    public ContainerResponse updateContainer(ContainerUpdateRequest request) {
        return null;
    }

    @Override
    public ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request) {
        return null;
    }

}
