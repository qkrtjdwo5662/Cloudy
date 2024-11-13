package com.cloudy.domain.serviceusage.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "서비스 생성", description = "서비스 생성 시 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServiceUsageCreateRequest {
    @Schema(description = "서비스 명", example = "/boards")
    @NotEmpty(message = "서비스명을 입력하세요.")
    private String serviceName;

    @Schema(description = "서비스 타입", example = "external")
    @NotEmpty(message = "서비스 타입을 입력하세요.")
    private String serviceType;

    @Schema(description = "서비스 비용", example = "100.0")
    @NotEmpty(message = "서비스 비용을 입력하세요.")
    private Double serviceCost;
}
