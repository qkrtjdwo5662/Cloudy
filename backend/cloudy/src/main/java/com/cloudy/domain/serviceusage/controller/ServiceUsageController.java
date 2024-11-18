package com.cloudy.domain.serviceusage.controller;

import com.cloudy.domain.container.model.dto.response.ContainerGetUseResponses;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageCreateRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageGetServiceCostRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceMonitoringResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageGetServiceCostResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUseGetResponses;
import com.cloudy.domain.serviceusage.service.ServiceUsageService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@Slf4j
@RequestMapping("/apiusages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://k11a606.p.ssafy.io:3000", allowCredentials = "true")
public class ServiceUsageController {

    private final ServiceUsageService serviceUsageService;

    @Operation(summary = "외부 서비스 사용량 갱신", description = "Super 계정을 기반으로 멤버 계정을 생성")
    @SwaggerApiSuccess(description = "외부 서비스 사용량 갱신 성공")
    @PostMapping("/services/usage")
    public Response<ServiceUsageResponse> updateServiceUsage(
            @Valid @RequestBody ServiceUsageRequest request,
            @Login Long memberId) {
        ServiceUsageResponse response = serviceUsageService.updateServiceUsage(request, memberId);
        return Response.SUCCESS(response, "Service usage updated successfully");
    }

    @Operation(summary = "서비스 사용량 조회 API", description = "컨테이너의 각 서비스 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/services/monitoring-count")
    public Response<ServiceUseGetResponses> getContainers(
            @Parameter(description = "컨테이너 ID", example = "1") @RequestParam Long containerId,
            @Parameter(description = "오늘 날짜와 시간(분 단위)", example = "2024-11-14 15:30") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") @RequestParam LocalDateTime dateTime,
            @Parameter(description = "시간 단위 (SECONDS, MINUTES, HOURS)", example = "MINUTES") @RequestParam String unit,
            @Parameter(description = "간격 (단위당 시간 간격)", example = "30") @RequestParam int interval,
            @Parameter(description = "개수 (반환할 리스트 크기)", example = "30") @RequestParam int count) {
        ServiceUseGetResponses response = serviceUsageService.getServicesUse(containerId, dateTime, ChronoUnit.valueOf(unit.toUpperCase()), interval, count);
        return Response.SUCCESS(response, "OK");
    }

    @Operation(summary = "서비스 모니터링 API", description = "컨테이너의 각 서비스를 모니터링합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 모니터링을 성공했습니다.")
    @GetMapping("/services/monitoring")
    public Response<ServiceMonitoringResponse> serviceMonitoring(
            @Parameter(description = "컨테이너 ID", example = "1") @RequestParam Long containerId,
            @Parameter(description = "오늘 날짜와 시간(분 단위)", example = "2024-11-14 15:30") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") @RequestParam LocalDateTime dateTime,
            @Parameter(description = "시간 단위 (SECONDS, MINUTES, HOURS)", example = "MINUTES") @RequestParam String unit,
            @Parameter(description = "간격 (단위당 시간 간격)", example = "30") @RequestParam int interval,
            @Parameter(description = "개수 (반환할 리스트 크기)", example = "30") @RequestParam int count) {
        ServiceMonitoringResponse response = serviceUsageService.serviceMonitoring(containerId, dateTime, ChronoUnit.valueOf(unit.toUpperCase()), interval, count);
        return Response.SUCCESS(response, "OK");
    }




    @Operation(summary = "서비스 생성", description = "내/외부 서비스 생성 api")
    @SwaggerApiSuccess(description = "서비스 생성 성공")
    @PostMapping("/create")
    public Response<ServiceUsageResponse> createService(
            @Valid @RequestBody ServiceUsageCreateRequest request) {
        ServiceUsageResponse response = serviceUsageService.createService(request);

        return Response.SUCCESS(response, "서비스 생성 성공");
    }

    @Operation(summary = "서비스 비용 호출", description = "외부 서비스 비용 호출")
    @SwaggerApiSuccess(description = "외부 서비스 비용 호출")
    @PostMapping("/external/cost")
    public Response<ServiceUsageGetServiceCostResponse> getServiceCost(
            @Valid @RequestBody ServiceUsageGetServiceCostRequest request) {
        ServiceUsageGetServiceCostResponse response = serviceUsageService.getServiceCost(request);
        return Response.SUCCESS(response, "서비스 생성 성공");
    }




}
