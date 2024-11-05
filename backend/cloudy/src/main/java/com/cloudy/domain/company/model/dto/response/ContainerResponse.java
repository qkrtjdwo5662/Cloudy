package com.cloudy.domain.company.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "컨테이너 응답 DTO", description = "컨테이너 갱신 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ContainerResponse {

    @Schema(description = "컨테이너 ID", example = "container123")
    private String containerId;

    @Schema(description = "컨테이너 이름", example = "MyContainer")
    private String containerName;
}