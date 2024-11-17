package com.cloudy.domain.server.model.dto.response;

import com.cloudy.domain.server.model.Server;
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

    @Schema(description = "클라우드 타입", example = "AWS")
    private String cloudType;

    @Schema(description = "인스턴스 종류", example = "t2.micro")
    private String instanceType;

    @Schema(description = "결제 방식", example = "On-Demand")
    private String paymentType;

    // Instance 필드에서 가져오는 값들
    @Schema(description = "CPU", example = "1")
    private String CPU;

    @Schema(description = "메모리", example = "1 GB")
    private String memory;

    @Schema(description = "인스턴스 스토리지", example = "EBS 전용")
    private String instanceStorage;

    @Schema(description = "네트워크 대역폭", example = "최대 12.5")
    private String networkBandwidth;

    // Server 엔티티로부터 ServerResponse 생성
    public static ServerResponse fromEntity(Server server) {
        return ServerResponse.builder()
                .serverId(server.getServerId())
                .serverName(server.getServerName())
                .cloudType(server.getInstance().getCloudType())
                .instanceType(server.getInstance().getInstanceName())
                .paymentType(server.getPaymentType())
                .CPU(server.getInstance().getCpu()) // Instance에서 가져온 정보
                .memory(server.getInstance().getMemory())
                .networkBandwidth(server.getInstance().getNetworkBandwidth())
                .build();
    }
}