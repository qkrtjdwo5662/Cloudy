package com.cloudy.domain.container.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.container.model.ContainerLog;
import com.cloudy.domain.container.model.dto.request.*;
import com.cloudy.domain.container.model.dto.response.*;
import com.cloudy.domain.container.repository.ContainerRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.server.repository.ServerRepository;
import com.cloudy.global.util.GenerateDateList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final ServerRepository serverRepository;
    private final ContainerRepository containerRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public ContainerGetUseResponses getContainersUse(Long serverId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count) {
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new NotFoundException("Server not found with ID: " + serverId));

        List<Container> containerList = containerRepository.findContainersByServerId(server);

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

        List<ContainerGetUseResponse> containerResponses = containerList.stream()
                .map(container -> {
                    String containerName = container.getContainerName();

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
                                                                .matchPhrase(mp -> mp
                                                                        .field("message")
                                                                        .query("container: " + containerName)
                                                                ))
                                                )
                                        ),
                                Map.class
                        );

                        // 총 히트 수 가져오기
                        long totalHits = searchResponse.hits().total().value();

                        return ContainerGetUseResponse.of(
                                container.getContainerId(),
                                containerName,
                                totalHits
                        );

                    } catch (Exception e) {
                        if (e.getMessage().contains("index_not_found_exception")) {
                            return ContainerGetUseResponse.of(
                                    container.getContainerId(),
                                    containerName,
                                    0L // 인덱스가 없을 경우 0으로 설정
                            );
                        } else {
                            e.printStackTrace();
                            return ContainerGetUseResponse.of(
                                    container.getContainerId(),
                                    containerName,
                                    0L
                            );
                        }
                    }
                })
                .toList();

        return ContainerGetUseResponses.from(containerResponses);
    }

    @Override
    public ContainerGetUseResponses getContainersUseRecentlyWeek(Long serverId, LocalDate date) {
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new NotFoundException("Server not found with ID: " + serverId));

        List<Container> containerList = containerRepository.findContainersByServerId(server);

        // 최근 1주일의 시작일과 종료일 계산
        LocalDate startDate = date.minusDays(6);  // 최근 일주일 (7일간의 데이터)
        LocalDate endDate = date;

        DateTimeFormatter esTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        DateTimeFormatter indexFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        List<ContainerGetUseResponse> containerResponses = containerList.stream()
                .map(container -> {
                    String containerName = container.getContainerName();
                    long totalHits = 0;

                    // 시작날부터 끝날까지 매일 인덱스를 조회하여 호출 수 누적
                    for (LocalDate currentDate = startDate; !currentDate.isAfter(endDate); currentDate = currentDate.plusDays(1)) {
                        String searchIndex = "server-logs-" + currentDate.format(indexFormatter) + "*";

                        // Elasticsearch 쿼리 시간 범위 설정
                        String gteTime = currentDate.atStartOfDay().minusHours(9).format(esTimeFormatter);
                        String ltTime = currentDate.plusDays(1).atStartOfDay().minusHours(9).format(esTimeFormatter);

                        try {
                            // Elasticsearch 쿼리 설정: 해당 날짜의 컨테이너 호출 수 조회
                            SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                                            .index(searchIndex)
                                            .query(q -> q
                                                    .bool(b -> b
                                                            .filter(f -> f
                                                                    .range(r -> r
                                                                            .field("@timestamp")
                                                                            .gte(JsonData.of(gteTime))
                                                                            .lt(JsonData.of(ltTime))
                                                                    ))
                                                            .must(m -> m
                                                                    .matchPhrase(mp -> mp
                                                                            .field("message")
                                                                            .query("container: " + containerName)
                                                                    ))
                                                    )
                                            ),
                                    Map.class
                            );

                            // 해당 날짜의 히트 수 누적
                            totalHits += searchResponse.hits().total().value();

                        } catch (Exception e) {
                            if (!e.getMessage().contains("index_not_found_exception")) {
                                e.printStackTrace();
                            }
                        }
                    }

                    return ContainerGetUseResponse.of(
                            container.getContainerId(),
                            containerName,
                            totalHits
                    );
                })
                .toList();

        return ContainerGetUseResponses.from(containerResponses);
    }


    @Override
    public Map<String,Long> getContainerUsages(ContainerGetUsagesRequest request) throws IOException {
        // 다중 인덱스 로그를 가져온다.
        Map<String, Long> hm = new TreeMap<>();
        // 1. 현재 타임스탬프를 가져온다.
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM");

        // 2. curFormatDate에서 1년전 데이터 ~~~~ Array 만들기~~
        List<String> logs = new ArrayList<>();

        for (int i =0; i< 12 ; i++) {
            LocalDateTime pastDate = now.minusMonths(i);
            String tempDate = "server-logs-"+pastDate.format(formatter);
            logs.add(tempDate);
        }

        // 3. 위 로그로, 모든 데이터 조회
        for (String log: logs){
            // 3-1. 문서기준
            try {
                SearchResponse<ContainerLog> sr = elasticsearchClient.search(s -> s
                        .index(log+"*") // 인덱스 지정
                        .query(q -> q.matchAll(t->t)), ContainerLog.class);


                long total = sr.hits().total().value();
                hm.put(log,total);
            }catch (Exception e){
                if (e.getMessage().contains("index_not_found_exception")) {
//                    System.out.println("Index not found, skipping: " + log);
                } else {
                    e.printStackTrace();
                }
            }


        }

        return hm;
    }

    @Override
    public ContainerGetMonthlyCostResponse getContainerMonthlyCosts(ContainerGetMonthlyCostRequest request) {
        return null;
    }

    @Override
    public ContainerGetDailyCostResponses getContainerDailyCosts(ContainerGetDailyCostRequest request) {
        return null;
    }

    @Override
    public ContainerGetServiceUsageResponses getContainerServiceUsages(ContainerGetServiceUsageRequest request) {
        return null;
    }

    @Override
    public ContainerUpdateNameResponse updateContainerName(ContainerUpdateNameRequest request) {
        return null;
    }

    @Override
    public void createContainer(ContainerCreateRequest containerCreateRequest, Long serverId) {
        Server server = serverRepository.findById(serverId).orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));;
        Container container = new Container(containerCreateRequest.getContainerName(), server);
        containerRepository.save(container);
    }

    @Override
    public Map<String, Long> getContainerUsageAgg(ContainerGetUsageDailyRequest request) {
        // request의 sortValues에 맞게 수행
        // 다중 인덱스 로그를 가져온다.
        Map<String, Long> hm = new TreeMap<>();
        // 1. 현재 년 월 일를 가져온다.
        LocalDate now = LocalDate.now();
        List<String> logs;

        GenerateDateList generateDateList = new GenerateDateList();
        if (request.getSortTypes().equals("Daily")){
            logs = generateDateList.getIndexBasedOnDate(now);
        }else if (request.getSortTypes().equals("Week")) {
            logs = generateDateList.getIndexBasedOnWeek(now);
        } else {
            return null;
        }

        // 3. 위 로그로, 모든 데이터 조회
        for (String log: logs){
            // 3-1. 문서기준
            String searchIndex = request.getSortTypes().equals("Week")? log+"*" : log;
            try {
                SearchResponse<ContainerLog> sr = elasticsearchClient.search(s -> s
                        .index(searchIndex) // 인덱스 지정
                                .query(q -> q
                                        .matchPhrase(m -> m
                                                .field("message")
                                                .query("external_service: true")
                                        )
                                ),
                        ContainerLog.class
                );
//                        .query(q -> q.matchAll(t->t)), ContainerLog.class);



                long total = sr.hits().total().value();
                hm.put(log,total);
            }catch (Exception e){
                if (e.getMessage().contains("index_not_found_exception")) {
//                    System.out.println("Index not found, skipping: " + log);
                    hm.put(log,0L);
                } else {
                    e.printStackTrace();
                }
            }


        }

        
        return hm;
    }

    @Override
    public ContainerGetResponses getContainers(Long serverId) {
        Server server = serverRepository.findById(serverId).orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));
        List<Container> containerList = containerRepository.findContainersByServerId(server);

        List<ContainerGetResponse> responses = containerList.stream()
                .map(container -> {
                    return ContainerGetResponse.of(
                            container.getContainerId(),
                            container.getContainerName()
                    );
                })
                .toList();

        return ContainerGetResponses.from(responses);
    }
}
