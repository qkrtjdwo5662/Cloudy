package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(name = "ContainerGetServiceUsageResponses", description = "컨테이너별 각 서비스 호출횟수 및 비용 조회 목록 DTO")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetServiceUsageResponses {

    @Schema(name = "containerGetUsagesResponse", description = "서버 전체 컨테이너 사용량 조회 목록")
    private List<ContainerGetServiceUsageResponse> containerGetServiceUsageResponses;

    @Schema(description = "목록 개수", example = "1")
    private int size;

    public static ContainerGetServiceUsageResponses from(List<ContainerGetServiceUsageResponse> containerGetServiceUsageResponses) {
        return new ContainerGetServiceUsageResponses(containerGetServiceUsageResponses, containerGetServiceUsageResponses.size());
    }
}
