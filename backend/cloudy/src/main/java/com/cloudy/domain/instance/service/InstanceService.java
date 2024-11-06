package com.cloudy.domain.instance.service;

import com.cloudy.domain.instance.model.dto.response.InstanceResponse;

import java.util.List;

public interface InstanceService {
    List<InstanceResponse> getInstanceList(String cloudType, String search);
}
