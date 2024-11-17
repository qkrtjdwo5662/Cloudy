package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(name = "서버 모니터링 응답 DTO", description = "서버 모니터링 리스트 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServerMonitoringResponse {
    private List<String> timeList;
    private List<Long> countList;

    public static ServerMonitoringResponse of(List<String> timeList, List<Long> countList){
        return new ServerMonitoringResponse(timeList, countList);
    }

}
