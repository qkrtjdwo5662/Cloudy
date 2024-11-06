package com.cloudy.domain.container.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ContainerDailyCostInfo", description = "알람 전체 조회 응답 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerDailyCostInfo {

    @Schema(description = "날짜", example ="12")
    @NotNull
    private Integer day;

    @Schema(description = "비용, 달러 기준", example ="34")
    @NotNull
    private Double cost;
}
