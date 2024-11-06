package com.cloudy.domain.instance.controller;

import com.cloudy.domain.instance.model.dto.response.InstanceResponse;
import com.cloudy.domain.instance.service.InstanceService;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/instances")
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;

    //todo: 시간되면 검색 뿐만 아니라 조건 필터링까지 구현
    @Operation(summary = "인스턴스 목록 조회", description = "클라우드 타입에 따라 인스턴스 목록을 조회하고 검색어가 있다면 검색")
    @SwaggerApiSuccess(description = "인스턴스 목록 조회 성공")
    @GetMapping
    public Response<List<InstanceResponse>> getInstanceList(
            @Parameter(description = "클라우드 타입", example = "AWS") @RequestParam String cloudType,
            @Parameter(description = "검색어", example = "test") @RequestParam(required = false) String search) {
        List<InstanceResponse> response = instanceService.getInstanceList(cloudType, search);
        return Response.SUCCESS(response, "Instance list retrieved successfully");
    }

}
