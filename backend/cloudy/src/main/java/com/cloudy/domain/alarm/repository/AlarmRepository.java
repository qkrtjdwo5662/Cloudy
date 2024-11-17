package com.cloudy.domain.alarm.repository;

import com.cloudy.domain.alarm.model.Alarm;
import com.cloudy.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    // 특정 멤버의 알람 목록 조회
    List<Alarm> findByMember(Member member);
}