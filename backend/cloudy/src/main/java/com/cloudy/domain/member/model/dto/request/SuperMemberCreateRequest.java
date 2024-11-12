package com.cloudy.domain.member.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "회원가입 request dto", description = "회원가입 시 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class SuperMemberCreateRequest {

    @Schema(description = "아이디", example = "asdf")
    @NotEmpty(message = "아이디를 입력하세요")
    private String loginId;

    @Schema(description = "비밀번호", example = "asdf")
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    @Schema(description = "부서명", example = "부서명")
    @NotEmpty(message = "부서명을 입력하세요.")
    private String departmentName;

    @Schema(description = "사업자 등록 번호", example = "1145930")
    @NotBlank
    private String businessRegistrationNumber; // 사업자 등록번호
}
