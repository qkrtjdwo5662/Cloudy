package com.cloudy.domain.server.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "로그인 사용자 서버 응답 DTO", description = "로그인 사용자 서버 조회 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServerLoginResponse {
    @Schema(description = "서버 ID", example = "1")
    private Long serverId;

    @Schema(description = "서버 이름", example = "스프링서버")
    private String serverName;

    public static ServerLoginResponse of(Long serverId, String serverName){
        return new ServerLoginResponse(serverId, serverName);
    }
}
