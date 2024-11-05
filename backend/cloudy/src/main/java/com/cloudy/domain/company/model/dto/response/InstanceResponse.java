package com.cloudy.domain.company.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "인스턴스 응답 DTO", description = "인스턴스 목록 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class InstanceResponse {

    @Schema(description = "인스턴스 ID", example = "i-1234567890abcdef")
    private String instanceId;

    @Schema(description = "인스턴스 이름", example = "MyInstance")
    private String instanceName;

    @Schema(description = "인스턴스 상태", example = "Running")
    private String status;

    @Schema(description = "클라우드 타입", example = "AWS")
    private String cloudType;
}