package com.cloudy.domain.member.model.dto.response;

import com.cloudy.domain.member.model.Role;
import com.cloudy.domain.server.model.Server;
import com.cloudy.global.config.security.jwt.JwtToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(name = "로그인 response dto", description = "로그인 후 반환되는 token 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberLoginResponse {

    @Schema(description = "access token", example = "token.token.token")
    private String accessToken;

    @Schema(description = "refresh token", example = "token.token.token")
    private String refreshToken;

    @Schema(description = "serverId", example = "1")
    private Long serverId;

    @Schema(description = "serverName", example = "스프링서버")
    private String serverName;

    @Schema(description = "role", example = "SUPER")
    private Role role;

    public static MemberLoginResponse of(String accessToken, String refreshToken, Long serverId, String serverName, Role role) {
        return new MemberLoginResponse(accessToken, refreshToken, serverId, serverName, role);
    }

    public static MemberLoginResponse from(JwtToken jwtToken, Server server, Role role) {
        return new MemberLoginResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken(), server.getServerId(), server.getServerName(), role);
    }
}
