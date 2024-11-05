package com.cloudy.domain.company.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "회사 응답 DTO", description = "회사 생성 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class CompanyResponse {

    @Schema(description = "회사 ID", example = "1")
    private Long companyId;

    @Schema(description = "회사 이름", example = "삼성전자")
    private String companyName;

    @Schema(description = "사업자 등록 번호", example = "123456789")
    private String businessRegistrationNumber;

    @Schema(description = "super 계정 ID", example = "admin@company.com")
    private String superAccountId;
}