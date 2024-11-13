package com.cloudy.domain.serviceusage.model.dto.response;

import com.cloudy.domain.serviceusage.model.ServiceUsage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "외부 서비스 비용 응답 DTO", description = "외부 서비스 비용 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class ServiceUsageGetServiceCostResponse {

    @Schema(description = "서비스 비용", example = "100.0")
    private Double serviceCost;

    public static ServiceUsageGetServiceCostResponse from(ServiceUsage serviceUsage){
        return new ServiceUsageGetServiceCostResponse(serviceUsage.getServiceCost());
    }
}
