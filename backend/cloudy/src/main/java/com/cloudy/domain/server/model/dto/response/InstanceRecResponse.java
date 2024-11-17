package com.cloudy.domain.server.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InstanceRecResponse {

    private String instanceName;
    private String cloudType;
    private double costPerHour;
    private double expectedUsage;


}
