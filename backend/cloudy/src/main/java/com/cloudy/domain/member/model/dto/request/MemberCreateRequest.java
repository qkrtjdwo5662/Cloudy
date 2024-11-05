package com.cloudy.domain.member.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "회원가입 request dto", description = "회원가입 시 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MemberCreateRequest {

    @Schema(description = "아이디", example = "asdf")
    @NotEmpty(message = "아이디를 입력하세요")
    private String loginId;

    @Schema(description = "비밀번호", example = "asdf")
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    @Schema(description = "부서명", example = "부서명")
    @NotEmpty(message = "부서명을 입력하세요.")
    private String departmentName;

    @Schema(description = "회사 id", example = "2")
    @NotNull(message = "회사 id를 입력하세요")
    private Long companyId;
}
