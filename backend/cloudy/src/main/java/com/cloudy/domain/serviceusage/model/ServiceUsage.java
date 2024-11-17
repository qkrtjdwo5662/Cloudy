package com.cloudy.domain.serviceusage.model;

import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.instance.model.Instance;
import com.cloudy.domain.member.model.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id", nullable = false)
    private Container container; // 멤버 객체 참조

    public ServiceUsage(String serviceType, String serviceName, Double serviceCost, Container container){
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        this.container = container;
    }
}
