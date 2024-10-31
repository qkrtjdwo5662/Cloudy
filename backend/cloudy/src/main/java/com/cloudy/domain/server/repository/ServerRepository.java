package com.cloudy.domain.server.repository;

import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
}
