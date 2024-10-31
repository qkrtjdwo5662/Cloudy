package com.cloudy.domain.serviceusage.repository;

import com.cloudy.domain.serviceusage.model.ServiceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUsageRepository extends JpaRepository<ServiceUsage, Long> {
}