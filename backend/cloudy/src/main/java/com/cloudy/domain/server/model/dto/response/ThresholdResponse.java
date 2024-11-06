package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "임계치 응답 DTO", description = "임계치 생성 또는 수정 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ThresholdResponse {

    @Schema(description = "서버 ID", example = "123")
    private String serverId;

    @Schema(description = "설정된 임계치 값", example = "80")
    private int limitValue;

    @Schema(description = "알림 사용 여부", example = "true")
    private boolean isUseAlarm;

    @Schema(description = "임계치 설정 시간", example = "2023-01-01T00:00:00")
    private String updatedAt;
}