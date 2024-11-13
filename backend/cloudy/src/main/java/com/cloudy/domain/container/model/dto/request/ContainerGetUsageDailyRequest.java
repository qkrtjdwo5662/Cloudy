package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Schema(hidden = true, name = "서버 전체 컨테이너 사용량 조회 주별, 일별 request dto", description = " 주별, 일별 서버 전체 컨테이너 사용량 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUsageDailyRequest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @NotNull(message = "서버 id를 입력하세요.")
    private Long serverId;

    @NotNull(message = "회원 id를 입력하세요.")
    private Long memberId;

    @NotNull(message = "Daily, Weeks 중 하나 선택")
    private String sortTypes;


    public ContainerGetUsageDailyRequest(Long serverId, Long memberId, String sortTypes) {
        this.serverId = serverId;
        this.memberId = memberId;
        this.sortTypes = sortTypes;
    }
}
