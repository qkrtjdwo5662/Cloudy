package com.cloudy.domain.instance.model.dto.response;

import com.cloudy.domain.instance.model.Instance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "인스턴스 상세 응답 DTO", description = "인스턴스 상세 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class InstanceDetailResponse {

    @Schema(description = "인스턴스 ID", example = "i-1234567890abcdef")
    private Long instanceId;

    @Schema(description = "인스턴스 이름", example = "MyInstance")
    private String instanceName;

    @Schema(description = "vCPU", example = "1")
    private String vCPU;

    @Schema(description = "메모리", example = "메모리")
    private String memory;

    @Schema(description = "인스턴스 스토리지", example = "EBS 전용")
    private String instanceStorage;

    @Schema(description = "네트워크 대역폭", example = "최대 12.5")
    private String networkBandwidth;


    // 정적 메서드로 엔티티에서 DTO 변환 지원
    public static InstanceDetailResponse fromEntity(Instance instance) {
        return InstanceDetailResponse.builder()
                .instanceId(instance.getInstanceId())
                .instanceName(instance.getInstanceName())
                .instanceStorage(instance.getInstanceStorage())
                .vCPU(instance.getCpu())
                .memory(instance.getMemory())
                .networkBandwidth(instance.getNetworkBandwidth())
                .build();
    }

}
