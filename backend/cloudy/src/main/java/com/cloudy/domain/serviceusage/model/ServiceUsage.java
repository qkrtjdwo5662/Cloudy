package com.cloudy.domain.serviceusage.model;

import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_usage")
@Getter
@NoArgsConstructor
public class ServiceUsage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceUsageId;

    private String serviceType; //todo: 내/외부 type, 수정 필요

    private String serviceName;

    private String serviceUsageHistory; //todo: 서비스 사용내역, 구체화 필요
}
