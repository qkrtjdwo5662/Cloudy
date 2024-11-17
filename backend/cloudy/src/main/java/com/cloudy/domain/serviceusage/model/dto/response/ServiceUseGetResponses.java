package com.cloudy.domain.serviceusage.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Schema(name = "ServiceUseGetResponses", description = "서비스 사용량 리스트 DTO")
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceUseGetResponses {
    @Schema(name = "ServiceUseGetResponses", description = "전체 서비스 조회 목록")
    private List<ServiceUseGetResponse> serviceUseGetResponses;

    public static ServiceUseGetResponses from(List<ServiceUseGetResponse> serviceUseGetResponses){
        return new ServiceUseGetResponses(serviceUseGetResponses);
    }
}
