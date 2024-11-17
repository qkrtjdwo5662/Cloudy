package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Schema(hidden = true, name = "해당 날짜의 컨테이너의 서비스별 비용, 사용량 조회 request dto", description = "해당 날짜의 컨테이너의 서비스별 비용, 사용량 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetServiceUsageRequest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotNull(message = "컨테이너 id를 입력하세요.")
    private Long containerId;

    @NotNull(message = "날짜를 입력하세요.")
    private LocalDate date;

    public ContainerGetServiceUsageRequest(Long containerId, String date) {
        this.containerId = containerId;
        this.date = LocalDate.parse(date, formatter);
    }
}
