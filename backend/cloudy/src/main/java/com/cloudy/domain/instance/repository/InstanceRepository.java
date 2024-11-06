package com.cloudy.domain.instance.repository;

import com.cloudy.domain.instance.model.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {
}
