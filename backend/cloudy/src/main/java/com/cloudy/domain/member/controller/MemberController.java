package com.cloudy.domain.member.controller;

import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponses;
import com.cloudy.domain.member.service.MemberService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiError;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.error.ErrorCode;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://k11a606.p.ssafy.io:3000", allowCredentials = "true")
public class MemberController {

    private final MemberService memberService;

    //todo: 추후 @PreAuthorize로 변경할 수도 있음. test용 api
    @Secured("ROLE_NORMAL")
    @Operation(hidden = true)
    @GetMapping("/normal/test")
    public Response<?> normalTest() {
        return Response.SUCCESS();
    }

    @Secured("ROLE_SUPER")
    @Operation(hidden = true)
    @GetMapping("/super/test")
    public Response<?> superTest() {
        return Response.SUCCESS();
    }

    @Operation(summary = "일반 회원 조회 api", description = "일반 회원 조회 get")
    @SwaggerApiSuccess(description = "일반 회원 조회 api")
    @GetMapping("/normal")
    public Response<NormalMemberGetResponses> getNormalMember(@Login Long memberId){
        NormalMemberGetResponses responses = memberService.getNormalMembers(memberId);
        return Response.SUCCESS(responses, "일반회원 리스트 조회 성공");
    }

    @Operation(summary = "일반 회원 삭제 api", description = "일반 회원 삭제")
    @SwaggerApiSuccess(description = "일반 회원 삭제 api")
    @DeleteMapping("/delete")
    public Response<NormalMemberGetResponses> deleteNormalMember(
            @Login Long sueprMemberId,
            @RequestParam Long nomalMemberId){
        memberService.deleteNormalMember(sueprMemberId, nomalMemberId);
        return Response.SUCCESS(null, "일반회원 삭제 성공");
    }

}
