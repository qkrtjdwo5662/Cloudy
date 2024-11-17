package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Schema(name = "ContainerGetResponses", description = "컨테이너 리스트 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetResponses {
    @Schema(name = "ContainerGetUseResponses", description = "전체 컨테이너 조회 목록")
    private List<ContainerGetResponse> containerGetResponses;

    public static ContainerGetResponses from(List<ContainerGetResponse> containerGetResponses){
        return new ContainerGetResponses(containerGetResponses);
    }
}
