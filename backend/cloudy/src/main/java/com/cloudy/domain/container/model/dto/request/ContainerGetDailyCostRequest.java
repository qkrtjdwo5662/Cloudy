package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Schema(hidden = true, name = "해당 날짜의 서버 전체 컨테이너의 비용 조회 request dto", description = "해당 날짜의 서버 전체 컨테이너의 비용 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetDailyCostRequest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotNull(message = "서버 id를 입력하세요.")
    private Long serverId;

    @NotNull(message = "날짜를 입력하세요.")
    private LocalDate date;

    public ContainerGetDailyCostRequest(Long serverId, String date) {
        this.serverId = serverId;
        this.date = LocalDate.parse(date, formatter);
    }
}
