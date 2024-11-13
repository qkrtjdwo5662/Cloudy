package com.cloudy.domain.alarm.service;

import com.cloudy.domain.alarm.model.dto.request.AlarmCreateRequest;
import com.cloudy.domain.alarm.model.dto.response.AlarmResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface AlarmService {
    List<AlarmResponse> getAlarms(Long memberId); // 회원 ID를 기반으로 알람 목록 조회
    AlarmResponse createAlarm(AlarmCreateRequest request, Long memberId); // 알람 생성
    AlarmResponse getAlarmDetail(Long alarmId); // 알람 상세 조회
    void markAlarmAsRead(Long alarmId, Long memberId); // 알람 읽음 처리
    SseEmitter subscribe(Long memberId);
    void sendAlarm(Long memberId, AlarmResponse alarmResponse);

    void disconnectAllEmitters();
}