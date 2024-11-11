package com.cloudy.domain.instance.repository;

import com.cloudy.domain.instance.model.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {

    Optional<Instance> findByInstanceName(String instanceName);

    List<Instance> findByCloudType(String cloudType);

    List<Instance> findByCloudTypeAndInstanceNameContaining(String cloudType, String instanceName);

    List<Instance> findByInstanceId(Long instanceId);

    Optional<Instance> findByInstanceNameAndInstancePeriodType(String instanceName, String InstancePeriodType);
}
