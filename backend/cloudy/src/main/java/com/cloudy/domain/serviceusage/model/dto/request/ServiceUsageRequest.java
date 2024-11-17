package com.cloudy.domain.serviceusage.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "외부 서비스 사용량 갱신 요청 DTO", description = "외부 서비스 사용량 갱신 시 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServiceUsageRequest {

    @Schema(description = "부서명", example = "기술부")
    @NotEmpty(message = "부서명을 입력하세요.")
    private String departmentName;

    @Schema(description = "멤버 ID (이메일 형식)", example = "member@company.com")
    @NotEmpty(message = "멤버 ID를 입력하세요.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String memberId;

    @Schema(description = "비밀번호", example = "password123!")
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
}
