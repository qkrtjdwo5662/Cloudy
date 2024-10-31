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

    private String instanceName;

    private String cpu;

    private String memory;

    private String performance;

    private String ondemand_fee;

    private String type;

    private String storage;

    private String storageType;

    private String osType;

    private String networkPerformance;

}
