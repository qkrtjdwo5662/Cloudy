package com.cloudy.domain.container.service;

import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public interface ContainerService {

    /*
    컨테이너 서비스 호출 횟수 조회
    비용 캘린더
    비용 캘린더 상세보기 (서비스별)
    비용 캘린더 컨테이너별 조회
    컨테이너 모니터링
    컨테이너 사용량 퍼센테이지 조회
    **/

    ContainerGetUseResponses getContainersUse(Long serverId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count);

    ContainerMonitoringResponse containerMonitoring(Long serverId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count);

    ContainerGetUseResponses getContainersUseRecentlyWeek(Long serverId, LocalDate date);

    //전체 컨테이너의 내/외부 서비스 사용량 조회, 전체 사용량 많은 순서로 정렬해서 보내줄 것.
    Map<String,Long> getContainerUsages(ContainerGetUsagesRequest request) throws IOException;

    //컨테이너 비용 캘린더 조회 (해당 일자까지의 일자별 전체 비용 조회)
    ContainerGetMonthlyCostResponse getContainerMonthlyCosts(ContainerGetMonthlyCostRequest request);

    //컨테이너별 비용 조회 (해당 일자의 컨테이너별 전체 서비스 비용 조회)
    ContainerGetDailyCostResponses getContainerDailyCosts(ContainerGetDailyCostRequest request);

    //컨테이너별 각 서비스 호출횟수 및 비용 조회
    ContainerGetServiceUsageResponses getContainerServiceUsages(ContainerGetServiceUsageRequest request);

    //컨테이너 이름 수정
    ContainerUpdateNameResponse updateContainerName(ContainerUpdateNameRequest request);

    void createContainer(ContainerCreateRequest containerCreateRequest, Long serverId);

    // Week , Daily에 맞게 사용량 리턴.
    Map<String, Long> getContainerUsageAgg(ContainerGetUsageDailyRequest request);

    ContainerGetResponses getContainers(Long serverId);
}
