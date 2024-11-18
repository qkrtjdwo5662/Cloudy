package com.cloudy.domain.serviceusage.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(name = "서비스 모니터링 응답 DTO", description = "서비스 모니터링 리스트 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServiceMonitoringResponse {
    private List<String> timeList;
    private List<List<Long>> countLists;
    private List<String> serviceNameList;

    public static ServiceMonitoringResponse of(List<String> timeList, List<List<Long>> countLists, List<String> serviceNameList){
        return new ServiceMonitoringResponse(timeList, countLists, serviceNameList);
    }
}
