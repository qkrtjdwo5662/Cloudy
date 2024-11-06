package com.cloudy.domain.instance.model.dto.response;

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

    @Schema(description = "vCPU", example = "1")
    private String vCPU;

    @Schema(description = "메모리", example = "메모리")
    private String memory;

    @Schema(description = "인스턴스 스토리지", example = "EBS 전용")
    private String instanceStorage;

    @Schema(description = "네트워크 대역폭", example = "최대 12.5")
    private String networkBandwidth;
}