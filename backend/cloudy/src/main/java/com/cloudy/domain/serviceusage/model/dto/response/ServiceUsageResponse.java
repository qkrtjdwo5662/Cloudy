package com.cloudy.domain.serviceusage.model.dto.response;

import com.cloudy.domain.serviceusage.model.ServiceUsage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "외부 서비스 사용량 갱신 응답 DTO", description = "외부 서비스 사용량 갱신 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServiceUsageResponse {

    @Schema(description = "서비스 ID", example = "1")
    private Long serviceUsageId;

    @Schema(description = "서비스 유형", example = "external")
    private String serviceType;

    @Schema(description = "서비스 이름", example = "/boards")
    private String serviceName;

    @Schema(description = "서비스 비용", example = "100.0")
    private Double serviceCost;

    public static ServiceUsageResponse from(ServiceUsage serviceUsage){
        return new ServiceUsageResponse(serviceUsage.getServiceUsageId(), serviceUsage.getServiceType(), serviceUsage.getServiceName(), serviceUsage.getServiceCost());
    }
}
