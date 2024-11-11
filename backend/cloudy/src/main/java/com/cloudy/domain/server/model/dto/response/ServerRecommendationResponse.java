package com.cloudy.domain.server.model.dto.response;

import com.cloudy.domain.server.model.Server;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerRecommendationResponse {

    private Long serverId;
    private String serverName;
    private String paymentType;
    private int serverLimit;

    public static ServerRecommendationResponse fromEntity(Server server) {
        return ServerRecommendationResponse.builder()
                .serverId(server.getServerId())
                .serverName(server.getServerName())
                .paymentType(server.getPaymentType())
                .serverLimit(server.getServerLimit())
                .build();
    }
}
