package com.cloudy.domain.company.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "회사 중복 체크 요청 DTO", description = "회사 중복 체크 시 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class CompanyDuplicateCheckRequest {

    @Schema(description = "사업자 등록 번호", example = "123456789")
    @NotEmpty(message = "사업자 등록 번호를 입력하세요.")
    @Size(min = 9, max = 9, message = "사업자 등록 번호는 9자리여야 합니다.")
    private String businessRegistrationNumber;

    @Schema(description = "회사 ID (이메일 형식)", example = "admin@company.com")
    @NotEmpty(message = "회사 ID를 입력하세요.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String id;
}