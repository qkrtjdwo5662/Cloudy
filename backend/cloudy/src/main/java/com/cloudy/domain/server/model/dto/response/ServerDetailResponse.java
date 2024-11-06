package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "서버 상세 응답 DTO", description = "서버 상세 정보 응답")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServerDetailResponse {

    @Schema(description = "서버 ID", example = "1")
    private Long serverId;

    @Schema(description = "클라우드 타입", example = "AWS")
    private String cloudType;

    @Schema(description = "인스턴스 종류", example = "t2.micro")
    private String instanceType;

    @Schema(description = "결제 방식", example = "On-Demand")
    private String paymentType;

    @Schema(description = "서버 상태", example = "Running")
    private String status;

    @Schema(description = "서버 생성일", example = "2023-01-01T00:00:00")
    private String createdAt;
}
