package com.cloudy.domain.container.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Schema(hidden = true, name = "서버 전체 컨테이너 사용량 조회 request dto", description = "서버 전체 컨테이너 사용량 조회")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerGetUsagesRequest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @NotNull(message = "서버 id를 입력하세요.")
    private Long serverId;

    @NotNull(message = "회원 id를 입력하세요.")
    private Long memberId;

    @NotNull(message = "시작일시를 입력하세요.")
    private LocalDateTime startTime;

    @NotNull(message = "종료일시를 입력하세요.")
    private LocalDateTime endTime;


    public ContainerGetUsagesRequest(Long serverId, Long memberId, String startTime, String endTime) {
        this.serverId = serverId;
        this.memberId = memberId;
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
    }

}
