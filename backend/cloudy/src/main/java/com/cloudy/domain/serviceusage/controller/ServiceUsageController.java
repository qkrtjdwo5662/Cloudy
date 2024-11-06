package com.cloudy.domain.serviceusage.controller;

import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import com.cloudy.domain.serviceusage.service.ServiceUsageService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/apiusages")
@RequiredArgsConstructor
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

}
