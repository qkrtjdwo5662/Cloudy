package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ContainerGetDailyCostResponse", description = "컨테이너별 비용 조회 (해당 일자의 컨테이너별 전체 서비스 비용 조회) 응답 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetDailyCostResponse {

    @Schema(description = "컨테이너 id", example ="1")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example ="웹 서버")
    private String containerName;

    @Schema(description = "비용, 달러 기준", example ="34")
    @NotNull
    private Double cost;

    public static ContainerGetDailyCostResponse of(Long containerId, String containerName, Double cost) {
        return new ContainerGetDailyCostResponse(containerId, containerName, cost);
    }
}
