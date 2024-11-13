package com.cloudy.domain.serviceusage.model;

import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_usage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUsage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceUsageId;

    private String serviceType;

    private String serviceName;

    private Double serviceCost;

    public ServiceUsage(String serviceType, String serviceName, Double serviceCost){
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }
}
