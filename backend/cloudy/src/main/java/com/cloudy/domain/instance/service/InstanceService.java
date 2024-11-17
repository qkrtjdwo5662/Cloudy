package com.cloudy.domain.instance.service;

import com.cloudy.domain.instance.model.dto.response.InstanceDetailResponse;
import com.cloudy.domain.instance.model.dto.response.InstanceTypeResponse;

import java.util.List;

public interface InstanceService {
    List<InstanceDetailResponse> getInstanceDetail(Long InstanceId);

    List<InstanceTypeResponse> getInstanceTypeList(String cloudType, String search);

    List<InstanceTypeResponse> getInstanceTypeList(String cloudType);
}
