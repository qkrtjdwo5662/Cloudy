package com.cloudy.domain.company.controller;

import com.cloudy.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

//    @Operation(summary = "회사 생성 API", description = "CompanyCreateRequest로 생성")
//    @SwaggerApiSuccess(description = "게시판 생성 성공")
    @PostMapping
    public Response<BoardResponse> createBoard(@Valid @RequestBody BoardCreateRequest request, @Login Integer memberId) {
        BoardResponse response = boardService.createBoard(request, memberId);
        return Response.SUCCESS(response, "Board created successfully");
    }
}
