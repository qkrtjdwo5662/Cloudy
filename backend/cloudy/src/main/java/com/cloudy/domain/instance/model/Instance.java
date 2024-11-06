package com.cloudy.domain.instance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instance")
@Getter
@NoArgsConstructor
public class Instance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instanceId;

    @Column(length = 100, nullable = false)
    private String instanceName;

    @Column(length = 20, nullable = false)
    private String cpu;

    @Column(length = 20, nullable = false)
    private String memory;

    @Column(length = 20, nullable = false)
    private String instancePeriodType;

    @Column(nullable = false)
    private double costPerHour;

    @Column(length = 20, nullable = false)
    private String cloudType;

    @Column(length = 20, nullable = false)
    private String osType;

    @Column(nullable = false)
    private double networkBandwidth;

    @Column(length = 20, nullable = false)
    private String location = "아시아 태평양(서울)";

}
