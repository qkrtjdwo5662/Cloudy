package com.cloudy.domain.server.controller;

import com.cloudy.domain.server.model.dto.request.*;
import com.cloudy.domain.server.model.dto.response.*;
import com.cloudy.domain.server.service.ServerService;
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

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/servers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://k11a606.p.ssafy.io:3000", allowCredentials = "true")
public class ServerController {

    private final ServerService serverService;

    @Operation(summary = "서버 등록", description = "클라우드 서비스, 인스턴스 종류, 결제 방식 등을 입력받아 서버 등록")
    @SwaggerApiSuccess(description = "서버 등록 성공")
    @PostMapping("/create")
    public Response<ServerResponse> createServer(@Valid @RequestBody ServerCreateRequest request,
                                                 @Login Long memberId) {
        System.out.println("memberId : " + memberId);
        ServerResponse response = serverService.createServer(request, memberId);
        return Response.SUCCESS(response, "Server created successfully");
    }

    @Operation(summary = "임계치 수정", description = "서버의 임계치를 수정")
    @SwaggerApiSuccess(description = "임계치 수정 성공")
    @PutMapping("/limit/update")
    public Response<ThresholdResponse> updateThreshold(@Valid @RequestBody ThresholdUpdateRequest request,
                                                       @Login Long memberId) {
        ThresholdResponse response = serverService.updateThreshold(request,memberId);
        return Response.SUCCESS(response, "Threshold updated successfully");
    }

    @Operation(summary = "서버 임계치 조회", description = "로그인한 회원의 서버 이름과 임계치를 조회합니다.")
    @SwaggerApiSuccess(description = "임계치 조회 성공")
    @GetMapping("/limit")
    public Response<List<ThresholdResponse>> getServerThresholds(@Login Long memberId) {
        List<ThresholdResponse> response = serverService.getThresholds(memberId);
        return Response.SUCCESS(response, "Threshold list retrieved successfully");
    }

    @Operation(summary = "서버 조회", description = "회원 ID를 기준으로 서버 목록 조회")
    @SwaggerApiSuccess(description = "서버 목록 조회 성공")
    @GetMapping
    public Response<List<ServerResponse>> getServers(@Login Long memberId) {
        List<ServerResponse> response = serverService.getServers(memberId);
        return Response.SUCCESS(response, "Server list retrieved successfully");
    }

    // todo: 안만들어도 될거같은데 혹시몰라서 냅둠.
//    @Operation(summary = "서버 상세 조회", description = "서버 ID를 기준으로 상세 정보 조회")
//    @SwaggerApiSuccess(description = "서버 상세 조회 성공")
//    @GetMapping("/{serverId}")
//    public Response<ServerDetailResponse> getServerDetail(
//            @Parameter(description = "서버 ID", example = "123") @PathVariable Long serverId) {
//        ServerDetailResponse response = serverService.getServerDetail(serverId);
//        return Response.SUCCESS(response, "Server detail retrieved successfully");
//    }

    @Operation(summary = "서버 삭제", description = "서버 아이디를 입력받아 삭제")
    @SwaggerApiSuccess(description = "서버 삭제 성공")
    @DeleteMapping("/update")
    public Response<ServerResponse> deleteServer(@Parameter(description = "서버 ID", example = "123") @RequestParam Long serverId,
                                                 @Login Long memberId) {
        ServerResponse response = serverService.deleteServer(serverId,memberId);
        return Response.SUCCESS(response, "Server updated successfully");
    }

    @Operation(summary = "서버 모니터링", description = "서버 모니터링 정보를 SSE를 통해 실시간으로 조회")
    @SwaggerApiSuccess(description = "서버 모니터링 조회 성공")
    @GetMapping("/monitoring")
    public Response<ServerMonitoringResponse> monitoringServer(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "오늘 날짜와 시간(분 단위)", example = "2024-11-14 15:30:20") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime dateTime,
            @Parameter(description = "시간 단위 (SECONDS, MINUTES, HOURS)", example = "MINUTES") @RequestParam String unit,
            @Parameter(description = "간격 (단위당 시간 간격)", example = "30") @RequestParam int interval,
            @Parameter(description = "개수 (반환할 리스트 크기)", example = "30") @RequestParam int count) {

        ServerMonitoringResponse response = serverService.monitorServer(serverId, dateTime, ChronoUnit.valueOf(unit.toUpperCase()), interval, count);
        return Response.SUCCESS(response, "Monitoring data retrieved successfully");
    }

    @Operation(summary = "서버 자원 사용량", description = "서버 자원(CPU, MEMORY) 사용량을 조회")
    @SwaggerApiSuccess(description = "서버 자원 사용량 조회 성공")
    @GetMapping("/monitoring/usage")
    public Response<CpuUsage> monitorServer(
            @Parameter(description = "serverId", example = "1") @RequestParam Long serverId) throws IOException {
//        CpuUsage response = serverService.getCPUData(serverId);
        CpuUsage response = serverService.getAllMemoryData(serverId);
        return Response.SUCCESS(response, "Monitoring data retrieved successfully");
    }

    @Operation(summary = "서버 비용 요약 조회", description = "서버 비용 요약 조회")
    @SwaggerApiSuccess(description = "서버 비용 요약 조회 성공")
    @GetMapping("/cost/summary")
    public Response<ServerMonthCostResponse> summaryCost(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "년,월,일", example = "2024-11-15") @DateTimeFormat(pattern = "yyyy-MM") @RequestParam LocalDate dateTime)  {

        ServerMonthCostResponse response = serverService.monthServerCost(serverId, dateTime);
        return Response.SUCCESS(response, "서버 비용 요약 조회 성공");
    }

    @Operation(summary = "서버 일자별 비용 조회", description = "서버 일자별 비용 조회")
    @SwaggerApiSuccess(description = "서버 일자별 비용 조회 성공")
    @GetMapping("/daily-cost")
    public Response<ServerDailyCostResponse> monthServerCost(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "년,월,일", example = "2024-11-15") @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate dateTime)  {

        ServerDailyCostResponse response = serverService.dailyServerCost(serverId, dateTime);
        return Response.SUCCESS(response, "서버 일자별 비용 조회 성공");
    }

    @Operation(summary = "서버 최근 일주일 비용 조회", description = "서버 최근 일주일 비용 조회")
    @SwaggerApiSuccess(description = "서버 최근 일주일 비용 조회 성공")
    @GetMapping("/week-cost")
    public Response<Map<String, Double>> recentlyWeekServerCost(
            @Parameter(description = "서버 ID", example = "1") @RequestParam Long serverId,
            @Parameter(description = "년,월,일", example = "2024-11-15") @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate dateTime)  {

        Map<String, Double> response = serverService.weeklyServerCost(serverId, dateTime);
        return Response.SUCCESS(response, "서버 최근 일주일 비용 조회 성공");
    }

    @Operation(summary = "서버 추천 리턴", description = "서버 추천 리턴")
    @SwaggerApiSuccess(description = "서버 추천 리턴 성공")
    @GetMapping("/recommend") // servers
    public Response<List<InstanceRecResponse>> getInstanceRecommendation(
            @Parameter(description = "serverId", example = "1") @RequestParam Long serverId) throws IOException {

        List<InstanceRecResponse> response = serverService.getInstanceRecommendation(serverId);
        return Response.SUCCESS(response, "서버 최근 일주일 비용 조회 성공");
    }

}
