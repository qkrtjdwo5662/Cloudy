package com.cloudy.domain.member.controller;

import com.cloudy.domain.member.service.MemberService;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
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
}
