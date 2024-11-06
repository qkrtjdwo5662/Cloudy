package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "서버 모니터링 응답 DTO", description = "서버 모니터링 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class MonitoringResponse {

    @Schema(description = "서버 ID", example = "123")
    private Long serverId;

    @Schema(description = "모니터링 기간", example = "7")
    private int duration;

    @Schema(description = "컨테이너 수", example = "3")
    private int containerCount;

    @Schema(description = "호출 횟수", example = "500")
    private int callCount;

    @Schema(description = "서버 내부 자원 사용량", example = "85")
    private int internalResourceUsage;

    @Schema(description = "서버 외부 자원 사용량", example = "90")
    private int externalResourceUsage;
}