package com.cloudy.domain.serviceusage.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.container.model.dto.response.ContainerGetUseResponse;
import com.cloudy.domain.container.model.dto.response.ContainerGetUseResponses;
import com.cloudy.domain.container.repository.ContainerRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.serviceusage.model.ServiceUsage;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageCreateRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageGetServiceCostRequest;
import com.cloudy.domain.serviceusage.model.dto.request.ServiceUsageRequest;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageGetServiceCostResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUsageResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUseGetResponse;
import com.cloudy.domain.serviceusage.model.dto.response.ServiceUseGetResponses;
import com.cloudy.domain.serviceusage.repository.ServiceUsageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUsageServiceImpl implements ServiceUsageService {
    private final ServiceUsageRepository serviceUsageRepository;
    private final ContainerRepository containerRepository;
    private final ElasticsearchClient elasticsearchClient;
    @Override
    public ServiceUsageResponse updateServiceUsage(ServiceUsageRequest request, Long memberId) {
        return null;
    }


    @Override
    public ServiceUsageResponse createService(ServiceUsageCreateRequest request) {
        Container container = containerRepository.findById(request.getContainerID()).orElseThrow(()-> new NotFoundException("not found"));
        ServiceUsage serviceUsage = new ServiceUsage(request.getServiceType(), request.getServiceName(), request.getServiceCost(), container);
        serviceUsageRepository.save(serviceUsage);

        return null;
    }

    @Override
    public ServiceUsageGetServiceCostResponse getServiceCost(ServiceUsageGetServiceCostRequest request) {
        ServiceUsage serviceUsage = serviceUsageRepository.findServiceUsageByServiceNameAndServiceType(request.getServiceName(), request.getServiceType());
        return ServiceUsageGetServiceCostResponse.from(serviceUsage);
    }

    @Override
    public ServiceUseGetResponses getServicesUse(Long containerId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count) {
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NotFoundException("Server not found with ID: " + containerId));

        List<ServiceUsage> serviceUsageList = serviceUsageRepository.findServiceUsagesByContainer(container);

        // 전체 기간의 시작 시간과 끝 시간 계산
        LocalDateTime adjustedDateTime = dateTime.minusHours(9); // 9시간 차이 보정
        LocalDateTime startTime = adjustedDateTime.minus(interval * (count - 1), unit);
        LocalDateTime endTime = adjustedDateTime;

        DateTimeFormatter esTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        DateTimeFormatter indexFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String gteTime = startTime.format(esTimeFormatter);
        String ltTime = endTime.format(esTimeFormatter);

        // 인덱스 패턴은 dateTime의 날짜 기준으로 설정
        String indexDate = dateTime.format(indexFormatter);
        String searchIndexPattern = "server-logs-" + indexDate + "*";

        List<ServiceUseGetResponse> serviceUsageResponses = serviceUsageList.stream()
                .map(serviceUsage -> {
                    String serviceName = serviceUsage.getServiceName();

                    try {
                        // Elasticsearch 쿼리 설정
                        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                                        .index(searchIndexPattern)
                                        .query(q -> q
                                                .bool(b -> b
                                                        .filter(f -> f
                                                                .range(r -> r
                                                                        .field("@timestamp")
                                                                        .gte(JsonData.of(gteTime))
                                                                        .lt(JsonData.of(ltTime))
                                                                ))
                                                        .must(m -> m
                                                                .match(mp -> mp
                                                                        .field("message")
                                                                        .query("API: " + serviceName)
                                                                ))
                                                )
                                        ),
                                Map.class
                        );

                        // 총 히트 수 가져오기
                        long totalHits = searchResponse.hits().total().value();

                        return ServiceUseGetResponse.of(
                                serviceUsage.getServiceUsageId(),
                                serviceName,
                                totalHits
                        );

                    } catch (Exception e) {
                        if (e.getMessage().contains("index_not_found_exception")) {
                            return ServiceUseGetResponse.of(
                                    serviceUsage.getServiceUsageId(),
                                    serviceName,
                                    0L // 인덱스가 없을 경우 0으로 설정
                            );
                        } else {
                            e.printStackTrace();
                            return ServiceUseGetResponse.of(
                                    serviceUsage.getServiceUsageId(),
                                    serviceName,
                                    0L
                            );
                        }
                    }
                })
                .toList();

        return ServiceUseGetResponses.from(serviceUsageResponses);
    }


}
