package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "컨테이너 이름 갱신 요청 DTO", description = "컨테이너 이름을 갱신할 때 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ContainerUpdateNameRequest {

    @Schema(description = "컨테이너 ID", example = "1")
    @NotEmpty(message = "컨테이너 ID를 입력하세요.")
    private Long containerId;

    @Schema(description = "컨테이너 이름", example = "container2")
    @NotEmpty(message = "컨테이너 이름을 입력하세요.")
    private String containerName;
}