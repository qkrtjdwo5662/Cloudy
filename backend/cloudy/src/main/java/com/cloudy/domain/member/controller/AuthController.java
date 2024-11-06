package com.cloudy.domain.member.controller;

import com.cloudy.domain.member.model.dto.request.MemberLoginRequest;
import com.cloudy.domain.member.model.dto.request.MemberCreateRequest;
import com.cloudy.domain.member.model.dto.request.MemberReissueRequest;
import com.cloudy.domain.member.model.dto.response.MemberLoginResponse;
import com.cloudy.domain.member.model.dto.response.MemberReissueTokenResponse;
import com.cloudy.domain.member.service.AuthService;
import com.cloudy.global.config.swagger.SwaggerApiError;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.error.ErrorCode;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 요청을 처리
     *
     * @param memberLoginRequest 로그인 요청 데이터
     * @return 로그인 응답 데이터 (JWT 토큰)
     */

    @Operation(summary = "로그인 API", description = "MemberLoginRequest로 로그인 진행")
    @SwaggerApiSuccess(description = "로그인 성공")
    @SwaggerApiError({ErrorCode.NOT_EXIST_MEMBER, ErrorCode.NOT_MATCH_PASSWORD})
    @PostMapping("/login")
    public Response<MemberLoginResponse> login(@Valid @RequestBody MemberLoginRequest memberLoginRequest) {
        MemberLoginResponse memberLoginResponse = authService.login(memberLoginRequest);
        return Response.SUCCESS(memberLoginResponse, "login을 성공했습니다.");
    }

    //todo: memberController로 옮기기.
    @Operation(summary = "일반 회원 회원가입 API", description = "MemberCreateRequest로 회원가입 진행, member/로 이동 예정. token 필요할 예정임.")
    @SwaggerApiSuccess(description = "일반 회원 회원가입 성공")
    @SwaggerApiError({ErrorCode.DUPLICATED_MEMBER})
    @PostMapping("/register/normal")
    public Response<?> normalRegister(@Valid @RequestBody MemberCreateRequest memberCreateRequest) {
        authService.normalRegister(memberCreateRequest);
        return Response.SUCCESS();
    }

    @Operation(summary = "슈퍼 회원 회원가입 API", description = "MemberCreateRequest로 회원가입 진행")
    @SwaggerApiSuccess(description = "일반 회원 회원가입 성공")
    @SwaggerApiError({ErrorCode.DUPLICATED_MEMBER})
    @PostMapping("/register/super")
    public Response<?> superRegister(@Valid @RequestBody MemberCreateRequest memberCreateRequest) {
        authService.superRegister(memberCreateRequest);
        return Response.SUCCESS();
    }

    @Operation(summary = "access token reissue API", description = "refresh token과 access token을 받아 새로운 access token을 발급합니다.")
    @SwaggerApiSuccess(description = "reissue 성공")
    @SwaggerApiError({ErrorCode.EXPIRED_JWT_TOKEN})
    @PostMapping("/reissue")
    public Response<MemberReissueTokenResponse> reissue(@RequestBody MemberReissueRequest request) {
        MemberReissueTokenResponse response = authService.reissueToken(request);
        return Response.SUCCESS(response, "access token을 재발급 했습니다.");
    }

    @Operation(summary = "login id 중복체크 API", description = "login id가 중복이면 true, 중복이 아니면 false를 반환합니다.")
    @GetMapping("/duplicate/loginid")
    public Response<Boolean> registerValidateDuplicate(@RequestParam("loginId") String loginId) {
        return Response.SUCCESS(authService.checkDuplicatedId((loginId)));
    }
}
