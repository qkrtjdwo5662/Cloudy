package com.cloudy.domain.server.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "임계치 갱신 요청 DTO", description = "임계치 갱신에 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ThresholdCreateRequest {

    @Schema(description = "서버 ID", example = "123")
    @NotEmpty(message = "서버 ID를 입력하세요.")
    private String serverId;

    @Schema(description = "임계치 값", example = "80")
    private int limitValue;

    @Schema(description = "알림 사용 여부", example = "true")
    private boolean isUseAlarm;
}