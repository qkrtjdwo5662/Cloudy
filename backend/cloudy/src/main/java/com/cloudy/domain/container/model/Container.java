package com.cloudy.domain.container.model;

import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.server.model.Server;
import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "container")
@Getter
@NoArgsConstructor
public class Container extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long containerId;

    @Column(length = 100, nullable = false)
    private String containerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false)
    private Server serverId;
}
