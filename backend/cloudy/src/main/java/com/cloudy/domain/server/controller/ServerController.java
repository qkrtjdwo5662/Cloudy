package com.cloudy.domain.server.controller;

import com.cloudy.domain.server.model.dto.request.ServerCreateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdUpdateRequest;
import com.cloudy.domain.server.model.dto.response.MonitoringResponse;
import com.cloudy.domain.server.model.dto.response.ServerResponse;
import com.cloudy.domain.server.model.dto.response.ThresholdResponse;
import com.cloudy.domain.server.service.ServerService;
import com.cloudy.global.config.guard.Login;
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
@RequestMapping("/servers")
@RequiredArgsConstructor
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

//    @Operation(summary = "임계치 생성", description = "서버의 임계치를 설정")
//    @SwaggerApiSuccess(description = "임계치 생성 성공")
//    @PostMapping("/limit/create")
//    public Response<ThresholdResponse> createThreshold(@Valid @RequestBody ThresholdCreateRequest request,
//                                                       @Login Long memberId) {
//        ThresholdResponse response = serverService.createThreshold(request, memberId);
//        return Response.SUCCESS(response, "Threshold created successfully");
//    }

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

    // todo: 일단 냅두고 추후에 수정할 예정
    @Operation(summary = "서버 모니터링", description = "서버 모니터링 정보를 SSE를 통해 실시간으로 조회")
    @SwaggerApiSuccess(description = "서버 모니터링 조회 성공")
    @GetMapping("/monitoring")
    public Response<MonitoringResponse> monitorServer(
            @Parameter(description = "서버 ID", example = "123") @RequestParam Long serverId,
            @Parameter(description = "모니터링 기간", example = "7") @RequestParam int duration) {
        MonitoringResponse response = serverService.monitorServer(serverId, duration);
        return Response.SUCCESS(response, "Monitoring data retrieved successfully");
    }
}
