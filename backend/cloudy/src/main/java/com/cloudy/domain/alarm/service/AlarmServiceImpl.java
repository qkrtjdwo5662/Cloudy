package com.cloudy.domain.alarm.service;

import com.cloudy.domain.alarm.model.Alarm;
import com.cloudy.domain.alarm.model.dto.request.AlarmCreateRequest;
import com.cloudy.domain.alarm.model.dto.response.AlarmResponse;
import com.cloudy.domain.alarm.repository.AlarmRepository;
import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public List<AlarmResponse> getAlarms(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        List<Alarm> alarms = alarmRepository.findByMember(member);
        return alarms.stream()
                .map(AlarmResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AlarmResponse createAlarm(AlarmCreateRequest request, Long memberId) {
        // 1. Member 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        // 2. Alarm 생성
        Alarm alarm = new Alarm(
                request.getServerName(),
                request.getTitle(),
                request.getContent(),
                false, // 읽음 처리 여부
                member
        );

        // 3. Alarm 저장
        Alarm savedAlarm = alarmRepository.save(alarm);

        // 4. SSE를 통해 실시간 알람 전송
        AlarmResponse response = AlarmResponse.fromEntity(savedAlarm);
        sendAlarm(memberId, response);

        // 5. 응답 반환
        return response;
    }

    @Override
    public AlarmResponse getAlarmDetail(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid alarm ID"));

        return AlarmResponse.fromEntity(alarm);
    }

    @Override
    public void markAlarmAsRead(Long alarmId, Long memberId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid alarm ID"));

        if (!alarm.getMember().getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("Unauthorized access to alarm");
        }

        alarm.setIsRead(true); // 읽음 처리
    }

    @Override
    public SseEmitter subscribe(Long memberId) {
        // SSE 연결 생성
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        // 연결 관리
        emitters.put(memberId, emitter);

        // 연결 종료 시 제거
        emitter.onCompletion(() -> emitters.remove(memberId));
        emitter.onTimeout(() -> emitters.remove(memberId));
        emitter.onError((e) -> emitters.remove(memberId));

        try {
            // 연결 초기화 메시지 전송
            emitter.send(SseEmitter.event()
                    .name("INIT")
                    .data("Connected"));
        } catch (IOException e) {
            emitters.remove(memberId);
        }

        return emitter;
    }

    @Override
    public void sendAlarm(Long memberId, AlarmResponse alarmResponse) {
        SseEmitter emitter = emitters.get(memberId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("ALARM")
                        .data(alarmResponse));
            } catch (IOException e) {
                emitters.remove(memberId);
            }
        }
    }

}