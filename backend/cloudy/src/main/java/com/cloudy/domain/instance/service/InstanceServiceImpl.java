package com.cloudy.domain.instance.service;

import com.cloudy.domain.instance.model.dto.response.InstanceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class InstanceServiceImpl implements InstanceService {
    @Override
    public List<InstanceResponse> getInstanceList(String cloudType, String search) {
        return List.of();
    }
}
