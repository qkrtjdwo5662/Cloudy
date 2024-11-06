package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ContainerGetServiceUsageResponse", description = "컨테이너별 각 서비스 호출횟수 및 비용 조회 응답 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetServiceUsageResponse {

    @Schema(description = "서비스 id", example ="1")
    private Long serviceId;

    @Schema(description = "서비스 이름", example ="PASS 인증")
    private String serviceName;

    @Schema(description = "호출 횟수", example ="34")
    @NotNull
    private Integer usage;

    @Schema(description = "비용, 달러 기준", example ="10.2")
    @NotNull
    private Double cost;

    public static ContainerGetServiceUsageResponse of(Long serviceId, String serviceName, Integer usage, Double cost) {
        return new ContainerGetServiceUsageResponse(serviceId, serviceName, usage, cost);
    }
}
