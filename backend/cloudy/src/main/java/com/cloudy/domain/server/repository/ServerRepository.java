package com.cloudy.domain.server.repository;

import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    List<Server> findByMember_MemberId(Long memberId);

    List<Server> findByMember_MemberIdOrderByServerId(Long memberId);

}
