package com.cloudy.domain.instance.model.dto.response;

import com.cloudy.domain.instance.model.Instance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "인스턴스 종류 응답 DTO", description = "인스턴스 종류 응답 정보")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class InstanceTypeResponse {

    @Schema(description = "인스턴스 ID", example = "i-1234567890abcdef")
    private Long instanceId;

    @Schema(description = "인스턴스 이름", example = "MyInstance")
    private String instanceName;

    @Schema(description = "클라우드 타입", example = "cloudType")
    private String cloudType;

    // 정적 메서드로 엔티티에서 DTO 변환 지원
    public static InstanceTypeResponse fromEntity(Instance instance) {
        return InstanceTypeResponse.builder()
                .instanceId(instance.getInstanceId())
                .instanceName(instance.getInstanceName())
                .cloudType(instance.getCloudType())
                .build();
    }

}