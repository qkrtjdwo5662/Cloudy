package com.cloudy.domain.member.model.dto.response;

import com.cloudy.domain.server.model.Server;
import com.cloudy.global.config.security.jwt.JwtToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(name = "로그인 response dto", description = "로그인 후 반환되는 token 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberLoginResponseOriginal {

    @Schema(description = "access token", example = "token.token.token")
    private String accessToken;

    @Schema(description = "refresh token", example = "token.token.token")
    private String refreshToken;


    public static MemberLoginResponseOriginal of(String accessToken, String refreshToken){
        return new MemberLoginResponseOriginal(accessToken, refreshToken);
    }

    public static MemberLoginResponseOriginal from(JwtToken jwtToken){
        return new MemberLoginResponseOriginal(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
