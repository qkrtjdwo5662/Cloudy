package com.cloudy.domain.member.repository;

import com.cloudy.domain.instance.model.Instance;
import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.model.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);

    Optional<Member> findByLoginId(String loginId);

    boolean existsBybusinessRegistrationNumber(String businessRegistrationNumber);

    List<Member> findMembersByBusinessRegistrationNumberAndRole(@NotBlank String businessRegistrationNumber, @NotBlank Role role);
}
