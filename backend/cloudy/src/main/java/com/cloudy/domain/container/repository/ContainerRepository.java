package com.cloudy.domain.container.repository;

import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    List<Container> findContainersByServerId(Server serverId);
}
