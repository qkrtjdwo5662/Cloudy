package com.cloudy.domain.container.controller;

import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;
import com.cloudy.domain.container.service.ContainerService;
import com.cloudy.domain.server.model.dto.response.ServerMonitoringResponse;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Tag(name = "컨테이너 관련 API")
@RestController
@Slf4j
@RequestMapping("/containers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://k11a606.p.ssafy.io:3000", allowCredentials = "true")
public class ContainerController {

    private final ContainerService containerService;

    @Operation(summary = "서버의 전체 컨테이너와 사용량 조회 API", description = "서버의 전체 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/usage")
    public Response<ContainerGetUseResponses> getContainers(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "오늘 날짜와 시간(분 단위)", example = "2024-11-14 15:30") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") @RequestParam LocalDateTime dateTime,
            @Parameter(description = "시간 단위 (SECONDS, MINUTES, HOURS)", example = "MINUTES") @RequestParam String unit,
            @Parameter(description = "간격 (단위당 시간 간격)", example = "30") @RequestParam int interval,
            @Parameter(description = "개수 (반환할 리스트 크기)", example = "30") @RequestParam int count) {
        ContainerGetUseResponses response = containerService.getContainersUse(serverId, dateTime, ChronoUnit.valueOf(unit.toUpperCase()), interval, count);
        return Response.SUCCESS(response, "OK");
    }


    @Operation(summary = "서버의 전체 컨테이너와 최근 일주일 사용량 조회 API", description = "서버의 전체 컨테이너 최근 일주일 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 최근 일주일 사용량 조회를 성공했습니다.")
    @GetMapping("/usage-week")
    public Response<ContainerGetUseResponses> getContainersRecentlyWeek(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "년,월,일", example = "2024-11-15") @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate date) {
        ContainerGetUseResponses response = containerService.getContainersUseRecentlyWeek(serverId, date);
        return Response.SUCCESS(response, "OK");
    }

    @Operation(summary = "컨테이너 생성", description = "서버의 컨테이너를 생성합니다.")
    @SwaggerApiSuccess(description = "컨테이너 생성을 성공하였습니다.")
    @PostMapping("/{serverId}/create")
    public Response<?> createContainer(@Valid @RequestBody ContainerCreateRequest containerCreateRequest, @PathVariable Long serverId){
        containerService.createContainer(containerCreateRequest, serverId);
        return Response.SUCCESS();
    }

    @Operation(summary = "컨테이너 조회", description = "서버의 모든 컨테이너를 조회 합니다.")
    @SwaggerApiSuccess(description = "컨테이너 조회를 성공하였습니다.")
    @GetMapping("/{serverId}")
    public Response<ContainerGetResponses> getContainers(@Parameter(description = "서버 ID", example = "1") @PathVariable Long serverId){
        ContainerGetResponses response = containerService.getContainers(serverId);
        return Response.SUCCESS(response, "컨테이너 조회를 성공하였습니다.");
    }



//    @Operation(summary = "서버 전체 컨테이너 사용량 조회 API", description = "서버 전체 컨테이너 사용량을 전체 조회합니다.")
//    @SwaggerApiSuccess(description = "서버 전체 컨테이너 사용량 조회를 성공했습니다.")
//    @GetMapping("/usage")
//    public Response<Map<String,Long>> getContainerUsages(@Parameter(name = "serverId", example = "1") @RequestParam("serverId") Long serverId,
//                                          @Login Long memberId,
//                                          @Parameter(name = "시작일시", example = "2024-11-03 14:27:00") @RequestParam String startDateTime,
//                                          @Parameter(name = "종료일시", example = "2024-11-04 14:27:00") @RequestParam String endDateTime) throws IOException {
//        Map<String,Long> response = containerService.getContainerUsages(new ContainerGetUsagesRequest(serverId, memberId, startDateTime, endDateTime));
//        return Response.SUCCESS(response);
//    }

    @Operation(summary = "서버 전체 컨테이너 하루당 사용량 조회 API", description = "하루당 서버 전체 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "하루당 서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/usage/daily")
    public Response<Map<String,Long>> getContainerUsageDaily(@Parameter(name = "serverId", example = "1") @RequestParam Long serverId,
                                                         @Login Long memberId) throws IOException {

        Map<String,Long> response = containerService.getContainerUsageAgg(new ContainerGetUsageDailyRequest(serverId, memberId , "Daily"));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "서버 전체 컨테이너 주별 사용량 조회 API", description = "주당 서버 전체 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "주당 서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/usage/week")
    public Response<Map<String,Long>> getContainerUsageWeek(@Parameter(name = "serverId", example = "1") @RequestParam("serverId") Long serverId,
                                                             @Login Long memberId) throws IOException {
        Map<String,Long> response = containerService.getContainerUsageAgg(new ContainerGetUsageDailyRequest(serverId, memberId, "Week"));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "서버 전체 컨테이너 사용량 조회 API", description = "오늘 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "오늘 서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping("/usage/today")
    public Response<Map<String,Long>> getContainerUsageToday(@Parameter(name = "serverId", example = "1") @RequestParam("serverId") Long serverId,
                                                            @Login Long memberId) throws IOException {
        Map<String,Long> response = containerService.getContainerUsageAgg(new ContainerGetUsageDailyRequest(serverId, memberId, "Today"));
        return Response.SUCCESS(response);
    }

    @Operation(summary = "컨테이너 리스트 각각 모니터링", description = "컨테이너를 통해 모니터링 api")
    @SwaggerApiSuccess(description = "컨테이너 리스트 모니터링 조회 성공")
    @GetMapping("/monitoring")
    public Response<ContainerMonitoringResponse> monitoringServer(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "오늘 날짜와 시간(시,분, 초 단위)", example = "2024-11-14 15:30:20") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime dateTime,
            @Parameter(description = "시간 단위 (SECONDS, MINUTES, HOURS)", example = "MINUTES") @RequestParam String unit,
            @Parameter(description = "간격 (단위당 시간 간격)", example = "30") @RequestParam int interval,
            @Parameter(description = "개수 (반환할 리스트 크기)", example = "30") @RequestParam int count) {

        ContainerMonitoringResponse response = containerService.containerMonitoring(serverId, dateTime, ChronoUnit.valueOf(unit.toUpperCase()), interval, count);
        return Response.SUCCESS(response, "Monitoring data retrieved successfully");
    }

//    @Operation(summary = "컨테이너 비용 캘린더 조회 API", description = "해당 일자까지의 일자별 전체 비용을 조회합니다.")
//    @SwaggerApiSuccess(description = "컨테이너 월간 비용 캘린더 조회를 성공했습니다.")
//    @GetMapping("/{containerId}/monthly-costs")
//    public Response<ContainerGetMonthlyCostResponse> getContainerMonthlyCosts(@Login Long memberId,
//                                          @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
//                                          @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
//        ContainerGetMonthlyCostResponse response = containerService.getContainerMonthlyCosts(new ContainerGetMonthlyCostRequest(containerId, date));
//        return Response.SUCCESS(response);
//    }
//
//    @Operation(summary = "컨테이너별 비용 조회 API", description = "해당 일자의 컨테이너별 전체 서비스 비용을 조회합니다.")
//    @SwaggerApiSuccess(description = "컨테이너별 날짜간 서비스 비용 조회를 성공했습니다.")
//    @GetMapping("/{serverId}/daily-costs")
//    public Response<ContainerGetDailyCostResponses> getContainerDailyCosts(@Login Long memberId,
//                                                @Parameter(name = "서버 id", example = "1") @PathVariable Long serverId,
//                                                @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
//        ContainerGetDailyCostResponses response = containerService.getContainerDailyCosts(new ContainerGetDailyCostRequest(serverId, date));
//        return Response.SUCCESS(response);
//    }
//
//    @Operation(summary = "컨테이너별 각 서비스 호출횟수 및 비용 조회 API", description = "컨테이너별 각 서비스 호출횟수 및 비용을 조회합니다.")
//    @SwaggerApiSuccess(description = "컨테이너별 각 서비스 호출횟수 및 비용 조회를 성공했습니다.")
//    @GetMapping("/{containerId}/service-usage")
//    public Response<ContainerGetServiceUsageResponses> getContainerServiceUsages(@Login Long memberId,
//                                                                           @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
//                                                                           @Parameter(name = "날짜", example = "2024-11-12") @RequestParam String date) {
//        ContainerGetServiceUsageResponses response = containerService.getContainerServiceUsages(new ContainerGetServiceUsageRequest(containerId, date));
//        return Response.SUCCESS(response);
//    }
//
//    @Operation(summary = "컨테이너별 각 서비스 호출횟수 및 비용 조회 API", description = "컨테이너별 각 서비스 호출횟수 및 비용을 조회합니다.")
//    @SwaggerApiSuccess(description = "컨테이너별 각 서비스 호출횟수 및 비용 조회를 성공했습니다.")
//    @PutMapping("/{containerId}/name")
//    public Response<ContainerUpdateNameResponse> updateContainerName(@Login Long memberId,
//                                                                                 @Parameter(name = "컨테이너 id", example = "1") @PathVariable Long containerId,
//                                                                                 @Parameter(name = "수정할 컨테이너 이름", example = "Nginx") @RequestParam String containerName) {
//        ContainerUpdateNameResponse response = containerService.updateContainerName(new ContainerUpdateNameRequest(containerId, containerName));
//        return Response.SUCCESS(response);
//    }


}
