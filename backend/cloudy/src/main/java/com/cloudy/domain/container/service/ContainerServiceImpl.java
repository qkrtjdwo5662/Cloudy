package com.cloudy.domain.container.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    @Autowired
    private final ServerRepository serverRepository;
    @Autowired
    private final ContainerRepository containerRepository;
    @Autowired
    private final ElasticsearchClient elasticsearchClient;

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
}
