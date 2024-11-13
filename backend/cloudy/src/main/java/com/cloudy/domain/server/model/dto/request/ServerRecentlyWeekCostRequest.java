package com.cloudy.domain.server.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(name = "서버 최근 일주일 비용 요약 요청", description = "서버 최근 일주일 비용 요약 요청 dto")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServerRecentlyWeekCostRequest {
    @Schema(description = "서버 Id", example = "1")
    @NotNull(message = "서버 아이디를 입력하세요.")
    private Long serverId;

    @Schema(description = "날짜", example = "2024-11-13")
    @NotNull(message = "서버 아이디를 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
