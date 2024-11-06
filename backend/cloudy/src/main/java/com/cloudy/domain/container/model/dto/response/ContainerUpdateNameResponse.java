package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "ContainerUpdateNameResponse", description = "컨테이너 이름 수정 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ContainerUpdateNameResponse {

    @Schema(description = "컨테이너 ID", example = "1")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example = "Nginx")
    private String containerName;
}