package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Schema(name = "ContainerGetUseResponses", description = "서버 전체 컨테이너 사용량 조회 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUseResponses {
    @Schema(name = "ContainerGetUseResponses", description = "서버 전체 컨테이너 사용량 조회 목록")
    private List<ContainerGetUseResponse> containerGetUseResponses;

    public static ContainerGetUseResponses from(List<ContainerGetUseResponse> containerGetUseResponses){
        return new ContainerGetUseResponses(containerGetUseResponses);
    }
}
