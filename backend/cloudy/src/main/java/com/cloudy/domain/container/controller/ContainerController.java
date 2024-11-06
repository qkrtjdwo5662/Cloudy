package com.cloudy.domain.container.controller;

import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;
import com.cloudy.domain.container.service.ContainerService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨테이너 관련 API")
@RestController
@Slf4j
@RequestMapping("/containers")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    @Operation(summary = "서버 전체 컨테이너 사용량 조회 API", description = "서버 전체 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/usage")
    public Response<ContainerGetUsagesResponses> getContainerUsages(@Parameter(name = "서버 id", example = "1") @RequestParam Long serverId,
                                          @Login Long memberId,
                                          @Parameter(name = "시작일시", example = "2024-11-03 14:27:00") @RequestParam String startDateTime,
                                          @Parameter(name = "종료일시", example = "2024-11-04 14:27:00") @RequestParam String endDateTime) {
        ContainerGetUsagesResponses response = containerService.getContainerUsages(new ContainerGetUsagesRequest(serverId, memberId, startDateTime, endDateTime));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "컨테이너 비용 캘린더 조회 API", description = "해당 일자까지의 일자별 전체 비용을 조회합니다.")
    @SwaggerApiSuccess(description = "컨테이너 비용 캘린더 조회를 성공했습니다.")
    @GetMapping("/{containerId}/monthly-costs")
    public Response<ContainerGetMonthlyCostResponse> getContainerMonthlyCosts(@Login Long memberId,
                                          @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
                                          @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
        ContainerGetMonthlyCostResponse response = containerService.getContainerMonthlyCosts(new ContainerGetMonthlyCostRequest(containerId, date));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "컨테이너별 비용 조회 API", description = "해당 일자의 컨테이너별 전체 서비스 비용을 조회합니다.")
    @SwaggerApiSuccess(description = "컨테이너별 전체 서비스 비용 조회를 성공했습니다.")
    @GetMapping("/{serverId}/daily-costs")
    public Response<ContainerGetDailyCostResponses> getContainerDailyCosts(@Login Long memberId,
                                                @Parameter(name = "서버 id", example = "1") @PathVariable Long serverId,
                                                @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
        ContainerGetDailyCostResponses response = containerService.getContainerDailyCosts(new ContainerGetDailyCostRequest(serverId, date));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "컨테이너별 각 서비스 호출횟수 및 비용 조회 API", description = "컨테이너별 각 서비스 호출횟수 및 비용을 조회합니다.")
    @SwaggerApiSuccess(description = "컨테이너별 각 서비스 호출횟수 및 비용 조회를 성공했습니다.")
    @GetMapping("/{containerId}/service-usage")
    public Response<ContainerGetServiceUsageResponses> getContainerServiceUsages(@Login Long memberId,
                                                                           @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
                                                                           @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
        ContainerGetServiceUsageResponses response = containerService.getContainerServiceUsages(new ContainerGetServiceUsageRequest(containerId, date));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "컨테이너별 각 서비스 호출횟수 및 비용 조회 API", description = "컨테이너별 각 서비스 호출횟수 및 비용을 조회합니다.")
    @SwaggerApiSuccess(description = "컨테이너별 각 서비스 호출횟수 및 비용 조회를 성공했습니다.")
    @PutMapping("/{containerId}/name")
    public Response<ContainerUpdateNameResponse> updateContainerName(@Login Long memberId,
                                                                                 @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
                                                                                 @Parameter(name = "수정할 컨테이너 이름", example = "Nginx") @RequestParam String containerName) {
        ContainerUpdateNameResponse response = containerService.updateContainerName(new ContainerUpdateNameRequest(containerId, containerName));
        return Response.SUCCESS(response);
    }

}
