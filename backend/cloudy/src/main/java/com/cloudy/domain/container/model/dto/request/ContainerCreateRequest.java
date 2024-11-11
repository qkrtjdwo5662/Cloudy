package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "컨테이너 생성 요청 DTO", description = "컨테이너 생성에 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ContainerCreateRequest {

    @Schema(description = "컨테이너 이름", example = "/")
    @NotEmpty(message = "컨테이너 이름을 입력하세요.")
    private String containerName;
}
