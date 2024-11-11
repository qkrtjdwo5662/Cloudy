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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Alarm alarm = new Alarm(
                request.getServerName(),
                request.getTitle(),
                request.getContent(),
                false,
                member
        );

        Alarm savedAlarm = alarmRepository.save(alarm);

        return AlarmResponse.fromEntity(savedAlarm);
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
}