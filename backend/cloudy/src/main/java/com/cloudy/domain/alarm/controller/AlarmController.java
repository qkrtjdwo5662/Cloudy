package com.cloudy.domain.alarm.controller;

import com.cloudy.domain.alarm.model.dto.request.AlarmCreateRequest;
import com.cloudy.domain.alarm.model.dto.response.AlarmResponse;
import com.cloudy.domain.alarm.service.AlarmService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(summary = "알람 목록 조회", description = "로그인한 사용자의 알람 목록을 조회합니다.")
    @SwaggerApiSuccess(description = "알람 목록 조회 성공")
    @GetMapping
    public Response<List<AlarmResponse>> getAlarms(@Login Long memberId) {
        List<AlarmResponse> alarms = alarmService.getAlarms(memberId);
        return Response.SUCCESS(alarms, "알람 목록 조회 성공");
    }

    @Operation(summary = "알람 추가", description = "새로운 알람을 생성합니다.")
    @SwaggerApiSuccess(description = "알람 추가 성공")
    @PostMapping
    public Response<AlarmResponse> createAlarm(
            @RequestBody AlarmCreateRequest request,
            @Login Long memberId) {
        AlarmResponse response = alarmService.createAlarm(request, memberId);
        return Response.SUCCESS(response, "알람 생성 성공");
    }

    @Operation(summary = "알람 상세 조회", description = "알람 ID를 사용하여 알람 상세 정보를 조회합니다.")
    @SwaggerApiSuccess(description = "알람 상세 조회 성공")
    @GetMapping("/{alarmId}")
    public Response<AlarmResponse> getAlarmDetail(@PathVariable Long alarmId) {
        AlarmResponse alarm = alarmService.getAlarmDetail(alarmId);
        return Response.SUCCESS(alarm, "알람 상세 조회 성공");
    }

    @Operation(summary = "알람 읽음 처리", description = "알람 ID를 사용하여 알람을 읽음 처리합니다.")
    @SwaggerApiSuccess(description = "알람 읽음 처리 성공")
    @PutMapping("/{alarmId}/read")
    public Response<Void> markAlarmAsRead(
            @PathVariable Long alarmId,
            @Login Long memberId) {
        alarmService.markAlarmAsRead(alarmId, memberId);
        return Response.SUCCESS(null, "알람 읽음 처리 성공");
    }

    @Operation(summary = "알람 SSE 구독", description = "로그인한 사용자의 알람을 실시간으로 구독합니다.")
    @GetMapping("/subscribe")
    public SseEmitter subscribe(@Login Long memberId) {
        return alarmService.subscribe(memberId);
    }

    @Operation(summary = "모든 SSE 연결 해제", description = "서버에서 모든 SSE 연결을 강제 종료합니다.")
    @PostMapping("/disconnect-all")
    public ResponseEntity<String> disconnectAllEmitters() {
        alarmService.disconnectAllEmitters(); // 모든 연결 해제
        return ResponseEntity.ok("모든 SSE 연결이 강제 종료되었습니다.");
    }

}