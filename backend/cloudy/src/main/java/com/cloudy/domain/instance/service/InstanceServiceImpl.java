package com.cloudy.domain.instance.service;

import com.cloudy.domain.instance.model.dto.response.InstanceDetailResponse;
import com.cloudy.domain.instance.model.dto.response.InstanceTypeResponse;
import com.cloudy.domain.instance.repository.InstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.cloudy.domain.instance.model.Instance;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class InstanceServiceImpl implements InstanceService {

    private final InstanceRepository instanceRepository;

    @Override
    public List<InstanceDetailResponse> getInstanceDetail(Long instanceId) {
        // 1. 인스턴스 조회
        Instance instance = instanceRepository.findById(instanceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid instance ID"));

        // 2. 관련 목록 상세 조회
        List<Instance> details = instanceRepository.findByInstanceId(instanceId);

        // 3. DTO 변환 및 반환
        return details.stream()
                .map(InstanceDetailResponse::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<InstanceTypeResponse> getInstanceTypeList(String cloudType) {
        List<Instance> instances = instanceRepository.findDistinctByCloudType(cloudType);

        // instanceName 기준으로 중복 제거
        return instances.stream()
                .collect(Collectors.toMap(
                        Instance::getInstanceName, // 키: instanceName
                        InstanceTypeResponse::fromEntity, // 값: DTO 변환
                        (existing, replacement) -> existing // 중복 시 기존 값 유지
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<InstanceTypeResponse> getInstanceTypeList(String cloudType, String search) {
        List<Instance> instances;

        if (search == null || search.isEmpty()) {
            // 검색어가 없을 때는 클라우드 타입만 필터링
            instances = instanceRepository.findByCloudType(cloudType);
        } else {
            // 검색어가 있을 때는 클라우드 타입과 검색어로 필터링
            instances = instanceRepository.findByCloudTypeAndInstanceNameContaining(cloudType, search);
        }

        // 인스턴스 종류만 포함한 응답 리스트 생성
        return instances.stream()
                .map(InstanceTypeResponse::fromEntity)
                .collect(Collectors.toList());
    }

}
