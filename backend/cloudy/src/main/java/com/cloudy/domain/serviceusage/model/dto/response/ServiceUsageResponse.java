package com.cloudy.domain.serviceusage.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "외부 서비스 사용량 갱신 응답 DTO", description = "외부 서비스 사용량 갱신 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServiceUsageResponse {

    @Schema(description = "멤버 ID", example = "member@company.com")
    private String memberId;

    @Schema(description = "부서명", example = "기술부")
    private String departmentName;

    @Schema(description = "멤버 계정 생성 성공 여부", example = "true")
    private boolean isSuccess;
}
