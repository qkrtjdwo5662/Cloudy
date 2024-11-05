package com.cloudy.domain.company.service;

import com.cloudy.domain.company.model.Company;
import com.cloudy.domain.company.model.dto.request.CompanyCreateRequest;
import com.cloudy.domain.company.model.dto.request.CompanyDuplicateCheckRequest;
import com.cloudy.domain.company.model.dto.request.ContainerUpdateRequest;
import com.cloudy.domain.company.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.company.model.dto.response.*;
import com.cloudy.domain.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(CompanyCreateRequest request) {
        Company company = companyRepository.save(Company.of(request));
        return CompanyResponse.of(company, request.getId());
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
