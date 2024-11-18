package com.cloudy.domain.instance.controller;

import com.cloudy.domain.instance.model.dto.response.InstanceDetailResponse;
import com.cloudy.domain.instance.model.dto.response.InstanceTypeResponse;
import com.cloudy.domain.instance.service.InstanceService;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/instances")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://k11a606.p.ssafy.io:3000", allowCredentials = "true")
public class InstanceController {

    private final InstanceService instanceService;

    @Operation(summary = "인스턴스 종류 조회", description = "클라우드 타입에 따라 인스턴스 종류를 조회합니다.")
    @SwaggerApiSuccess(description = "인스턴스 종류 조회 성공")
    @GetMapping("/type")
    public Response<List<InstanceTypeResponse>> getInstanceList(
            @Parameter(description = "클라우드 타입", example = "AWS") @RequestParam String cloudType) {
        List<InstanceTypeResponse> response = instanceService.getInstanceTypeList(cloudType);
        return Response.SUCCESS(response, "Instance list retrieved successfully");
    }

    @Operation(summary = "인스턴스 목록 상세 조회", description = "인스턴스 종류에 따라 해당하는 목록을 상세 조회")
    @SwaggerApiSuccess(description = "인스턴스 목록 상세 조회 성공")
    @GetMapping("/details")
    public Response<List<InstanceDetailResponse>> getInstanceDetail(
            @Parameter(description = "인스턴스 Id", example = "1") @RequestParam Long instanceId) {
        List<InstanceDetailResponse> response = instanceService.getInstanceDetail(instanceId);
        return Response.SUCCESS(response, "Instance list retrieved successfully");
    }

}
