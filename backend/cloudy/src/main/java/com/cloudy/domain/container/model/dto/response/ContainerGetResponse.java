package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ContainerGetResponse", description = "컨테이너 응답 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetResponse {
    @Schema(description = "컨테이너 id", example ="1")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example ="웹 서버")
    private String containerName;

    public static ContainerGetResponse of(Long containerId, String containerName){
        return new ContainerGetResponse(containerId, containerName);
    }
}
