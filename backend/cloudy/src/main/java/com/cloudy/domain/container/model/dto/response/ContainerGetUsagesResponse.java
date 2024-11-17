package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ContainerGetUsagesResponse", description = "서버 전체 컨테이너 사용량 조회 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUsagesResponse {

    @Schema(description = "컨테이너 id", example ="1")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example ="웹 서버")
    private String containerName;

    @Schema(description = "내부 서비스 호출량", example ="10")
    private Long internalServiceRequestCount;

    @Schema(description = "내부 서비스 호출량", example ="10")
    private Long externalServiceRequestCount;

    public static ContainerGetUsagesResponse of(Long containerId, String containerName, Long internalServiceRequestCount, Long externalServiceRequestCount) {
        return new ContainerGetUsagesResponse(containerId, containerName, internalServiceRequestCount, externalServiceRequestCount);
    }
}
