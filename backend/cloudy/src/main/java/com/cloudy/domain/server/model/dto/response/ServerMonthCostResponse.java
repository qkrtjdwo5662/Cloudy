package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "서버 월 비용", description = "서버 월 비용 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServerMonthCostResponse {

    @Schema(description = "월 누적 비용", example = "100.00")
    private Double accumulatedCost;

    @Schema(description = "월 예상 비용", example = "140.00")
    private Double expectedCost;

}
