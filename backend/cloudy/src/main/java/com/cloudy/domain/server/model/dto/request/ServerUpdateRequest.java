package com.cloudy.domain.server.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "서버 갱신 요청 DTO", description = "서버 갱신에 필요한 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ServerUpdateRequest {

    @Schema(description = "서버 이름", example = "스프링 서버")
    @NotEmpty(message = "서버 이름을 입력하세요.")
    private String serverName;

    @Schema(description = "클라우드 타입", example = "AWS")
    @NotEmpty(message = "클라우드 타입을 입력하세요.")
    private String cloudType;

    @Schema(description = "인스턴스 종류", example = "t2.micro")
    @NotEmpty(message = "인스턴스 종류를 입력하세요.")
    private String instanceType;

    @Schema(description = "결제 방식", example = "On-Demand")
    @NotEmpty(message = "결제 방식을 입력하세요.")
    private String paymentType;
}