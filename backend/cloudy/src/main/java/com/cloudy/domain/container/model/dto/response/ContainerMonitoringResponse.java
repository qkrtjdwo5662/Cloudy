package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(name = "컨테이너 모니터링 응답 DTO", description = "컨테이너 모니터링 리스트 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ContainerMonitoringResponse {
    private List<String> timeList;
    private List<List<Long>> countLists;
    private List<String> containerNameList;

    public static ContainerMonitoringResponse of(List<String> timeList, List<List<Long>> countLists, List<String> containerNameList){
        return new ContainerMonitoringResponse(timeList, countLists, containerNameList);
    }
}
