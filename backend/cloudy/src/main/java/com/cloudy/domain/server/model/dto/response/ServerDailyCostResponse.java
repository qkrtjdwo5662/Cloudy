package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "서버 일자 비용", description = "서버 일자 비용 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServerDailyCostResponse {

    @Schema(description = "비용", example = "100.00")
    private Double cost;
}
