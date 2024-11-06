package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Schema(name = "ContainerGetMonthlyCostResponse", description = "컨테이너 비용 캘린더 조회 (해당 일자까지의 일자별 전체 비용 조회) DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetMonthlyCostResponse {

    @Schema(description = "컨테이너 id", example ="1")
    private Long containerId;

    @Schema(description = "컨테이너 일자별 비용 list")
    private List<ContainerDailyCostInfo> containerDailyCostInfos;

    @Schema(description = "list size", example ="1")
    private Integer containerDailyCostInfosSize;
}
