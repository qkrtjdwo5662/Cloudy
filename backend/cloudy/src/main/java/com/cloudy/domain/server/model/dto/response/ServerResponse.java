package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "서버 응답 DTO", description = "서버 생성 또는 조회 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServerResponse {

    @Schema(description = "서버 ID", example = "1")
    private Long serverId;

    @Schema(description = "서버 이름", example = "스프링서버")
    private String serverName;

    @Schema(description = "인스턴스 종류", example = "t2.micro")
    private String instanceType;

    @Schema(description = "vCPU", example = "1")
    private String vCPU;

    @Schema(description = "메모리", example = "메모리")
    private String memory;

    @Schema(description = "인스턴스 스토리지", example = "EBS 전용")
    private String instanceStorage;

    @Schema(description = "네트워크 대역폭", example = "최대 12.5")
    private String networkBandwidth;

    @Schema(description = "결제 방식", example = "On-Demand")
    private String paymentType;
}