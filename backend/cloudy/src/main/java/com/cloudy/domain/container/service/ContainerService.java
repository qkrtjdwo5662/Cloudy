package com.cloudy.domain.container.service;

import com.cloudy.domain.container.model.dto.request.ContainerGetUsagesRequest;
import com.cloudy.domain.container.model.dto.response.ContainerGetUsagesResponses;

public interface ContainerService {

    /*
    컨테이너 서비스 호출 횟수 조회
    비용 캘린더
    비용 캘린더 상세보기 (서비스별)
    비용 캘린더 컨테이너별 조회
    컨테이너 모니터링
    컨테이너 사용량 퍼센테이지 조회
    **/

    //전체 컨테이너의 내/외부 서비스 사용량 조회, 전체 사용량 많은 순서로 정렬해서 보내줄 것.
    ContainerGetUsagesResponses getContainerUsages(ContainerGetUsagesRequest request);




}
