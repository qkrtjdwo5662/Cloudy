package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(name = "ContainerGetUsagesResponses", description = "서버 전체 컨테이너 사용량 조회 목록 DTO")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUsagesResponses {

    @Schema(name = "ContainerGetUsagesResponse", description = "서버 전체 컨테이너 사용량 조회 목록")
    private List<ContainerGetUsagesResponse> containerGetUsagesResponses;

    @Schema(description = "목록 개수", example = "1")
    private int size;

    public static ContainerGetUsagesResponses from(List<ContainerGetUsagesResponse> containerGetUsagesResponses) {
        return new ContainerGetUsagesResponses(containerGetUsagesResponses, containerGetUsagesResponses.size());
    }
}
