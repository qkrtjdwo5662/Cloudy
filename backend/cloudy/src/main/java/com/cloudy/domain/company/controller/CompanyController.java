package com.cloudy.domain.company.controller;

import com.cloudy.domain.company.model.dto.request.CompanyCreateRequest;
import com.cloudy.domain.company.model.dto.request.CompanyDuplicateCheckRequest;
import com.cloudy.domain.company.model.dto.request.ContainerUpdateRequest;
import com.cloudy.domain.company.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.company.model.dto.response.*;
import com.cloudy.domain.company.service.CompanyService;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "회사 생성 API", description = "CompanyCreateRequest로 생성 후 super 계정 생성")
    @SwaggerApiSuccess(description = "회사 생성 성공")
    @PostMapping
    public Response<CompanyResponse> createCompany(@Valid @RequestBody CompanyCreateRequest request) {
        CompanyResponse response = companyService.createCompany(request);
        return Response.SUCCESS(response, "Company created successfully");
    }

    @Operation(summary = "회사 중복 체크", description = "사업자 등록 번호 및 id를 중복 체크")
    @SwaggerApiSuccess(description = "회사 중복 체크 성공")
    @GetMapping("/duplicate/check")
    public Response<CompanyDuplicateCheckResponse> checkDuplicate(
            @Parameter(description = "회사 중복 체크 요청", required = true) @Valid CompanyDuplicateCheckRequest request) {
        CompanyDuplicateCheckResponse response = companyService.checkDuplicate(request);
        return Response.SUCCESS(response, "Company duplicate check completed");
    }

    //todo: 시간되면 검색 뿐만 아니라 조건 필터링까지 구현
    @Operation(summary = "인스턴스 목록 조회", description = "클라우드 타입에 따라 인스턴스 목록을 조회하고 검색어가 있다면 검색")
    @SwaggerApiSuccess(description = "인스턴스 목록 조회 성공")
    @GetMapping("/instances")
    public Response<List<InstanceResponse>> getInstanceList(
            @Parameter(description = "클라우드 타입", example = "AWS") @RequestParam String cloudType,
            @Parameter(description = "검색어", example = "test") @RequestParam(required = false) String search) {
        List<InstanceResponse> response = companyService.getInstanceList(cloudType, search);
        return Response.SUCCESS(response, "Instance list retrieved successfully");
    }

    @Operation(summary = "컨테이너 갱신", description = "컨테이너의 이름 등의 정보를 갱신")
    @SwaggerApiSuccess(description = "컨테이너 갱신 성공")
    @PutMapping("/containers")
    public Response<ContainerResponse> updateContainer(
            @Valid @RequestBody ContainerUpdateRequest request) {
        ContainerResponse response = companyService.updateContainer(request);
        return Response.SUCCESS(response, "Container updated successfully");
    }

    @Operation(summary = "외부 서비스 사용량 갱신", description = "Super 계정을 기반으로 멤버 계정을 생성")
    @SwaggerApiSuccess(description = "외부 서비스 사용량 갱신 성공")
    @PostMapping("/services/usage")
    public Response<ServiceUsageResponse> updateServiceUsage(
            @Valid @RequestBody ServiceUsageRequest request) {
        ServiceUsageResponse response = companyService.updateServiceUsage(request);
        return Response.SUCCESS(response, "Service usage updated successfully");
    }

}
