package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Schema(name = "ContainerGetUseResponse", description = "서버 전체 컨테이너 사용량 조회 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUseResponse {

    @Schema(description = "컨테이너 id", example ="1")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example ="웹 서버")
    private String containerName;

    @Schema(description = "서비스 호출량", example ="10")
    private Long serviceRequestCount;

    public static ContainerGetUseResponse of(Long containerId, String containerName, Long serviceRequestCount){
        return new ContainerGetUseResponse(containerId, containerName, serviceRequestCount);
    }
}
