package com.cloudy.domain.serviceusage.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Schema(name = "ServiceUseGetResponse", description = "서비스 사용량 조회 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceUseGetResponse {
    @Schema(description = "서비스 id", example ="1")
    private Long serviceId;

    @Schema(description = "서비스 명", example ="/auth/sms/send")
    private String serviceName;

    @Schema(description = "서비스 호출량", example ="10")
    private Long count;

    public static ServiceUseGetResponse of(Long serviceId, String serviceName, Long count){
        return new ServiceUseGetResponse(serviceId, serviceName, count);
    }
}
