package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Schema(hidden = true, name = "컨테이너 비용 캘린더 조회 request dto", description = "컨테이너 비용 캘린더 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetMonthlyCostRequest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotNull(message = "컨테이너 id를 입력하세요.")
    private Long containerId;

    @NotNull(message = "날짜를 입력하세요.")
    private LocalDate date;

    public ContainerGetMonthlyCostRequest(Long containerId, String date) {
        this.containerId = containerId;
        this.date = LocalDate.parse(date, formatter);
    }
}
