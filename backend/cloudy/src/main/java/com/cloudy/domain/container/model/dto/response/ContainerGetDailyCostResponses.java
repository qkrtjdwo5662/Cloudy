package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(name = "ContainerGetDailyCostResponses", description = "컨테이너별 비용 조회 (해당 일자의 컨테이너별 전체 서비스 비용 조회) 목록 DTO")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetDailyCostResponses {

    @Schema(name = "ContainerGetDailyCostResponse", description = "서버 전체 컨테이너 사용량 조회 목록")
    private List<ContainerGetDailyCostResponse> containerGetDailyCostResponses;

    @Schema(description = "목록 개수", example = "1")
    private int size;

    public static ContainerGetDailyCostResponses from(List<ContainerGetDailyCostResponse> containerGetDailyCostResponses) {
        return new ContainerGetDailyCostResponses(containerGetDailyCostResponses, containerGetDailyCostResponses.size());
    }
}
