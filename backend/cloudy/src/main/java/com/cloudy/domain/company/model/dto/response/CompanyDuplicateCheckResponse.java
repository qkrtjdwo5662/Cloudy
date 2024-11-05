package com.cloudy.domain.company.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "회사 중복 체크 응답 DTO", description = "회사 중복 체크 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class CompanyDuplicateCheckResponse {

    @Schema(description = "사업자 등록 번호 중복 여부", example = "false")
    private boolean isBusinessRegistrationNumberDuplicated;

    @Schema(description = "회사 ID 중복 여부", example = "true")
    private boolean isIdDuplicated;
}