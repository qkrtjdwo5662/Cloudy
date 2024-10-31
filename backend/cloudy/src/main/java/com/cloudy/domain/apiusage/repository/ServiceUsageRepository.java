package com.cloudy.domain.apiusage.repository;

import com.cloudy.domain.apiusage.model.ServiceUsage;
import com.cloudy.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUsageRepository extends JpaRepository<ServiceUsage, Long> {
}
