package com.cloudy.domain.server.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.cloudy.domain.container.model.Container;
import com.cloudy.domain.container.repository.ContainerRepository;
import com.cloudy.domain.instance.model.Instance;
import com.cloudy.domain.instance.repository.InstanceRepository;
import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.model.Role;
import com.cloudy.domain.member.repository.MemberRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.server.model.dto.request.*;
import com.cloudy.domain.server.model.dto.response.*;
import com.cloudy.domain.server.repository.ServerRepository;
import com.cloudy.domain.serviceusage.model.ServiceUsage;
import com.cloudy.domain.serviceusage.repository.ServiceUsageRepository;
import com.cloudy.global.util.DockerStatsParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final MemberRepository memberRepository;
    private final InstanceRepository instanceRepository;
    private final ElasticsearchClient elasticsearchClient;
    private final ServiceUsageRepository serviceUsageRepository;
    private final ContainerRepository containerRepository;

    @Override
    public ServerResponse createServer(ServerCreateRequest request, Long memberId) {
//        System.out.println(request.getInstanceType() + " " +request.getPaymentType());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Instance instance = instanceRepository.findByInstanceNameAndInstancePeriodType(request.getInstanceType(), request.getPaymentType())
                .orElseThrow(() -> new IllegalArgumentException("Invalid instance type"));

        Server server = new Server(request.getServerName(), 0, request.getPaymentType(), member, instance);
        serverRepository.save(server);

        return null;
    }

    @Override
    public ThresholdResponse updateThreshold(ThresholdUpdateRequest request, Long memberId) {
        Server server = serverRepository.findById(request.getServerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));

        // 서버 임계치 설정
        server.setServerLimit(request.getUpdatedLimitValue());

        // 응답 생성
        return new ThresholdResponse(server.getServerId(), server.getServerName() , server.getServerLimit(), request.isUseAlarm(), LocalDateTime.now());
    }

    @Override
    public List<ServerResponse> getServers(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member Not Found"));
        List<Server> servers = List.of();

        if(member.getRole().equals(Role.SUPER)){
            servers = serverRepository.findByMember_MemberId(memberId);
        }else if(member.getRole().equals(Role.NORMAL)){
            Member superMember = memberRepository.findMemberByBusinessRegistrationNumberAndRole(member.getBusinessRegistrationNumber(), Role.SUPER);
            servers = serverRepository.findByMember_MemberId(superMember.getMemberId());
        }



        return servers.stream()
                .map(ServerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ServerDetailResponse getServerDetail(Long serverId) {

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));

        return new ServerDetailResponse(
                server.getServerId(),
                server.getServerName(),
                server.getInstance().getCloudType(),
                server.getInstance().getInstanceName(),
                server.getPaymentType(),
                server.getCreatedAt()
        );
    }

    @Override
    public ServerResponse deleteServer(Long serverId, Long memberId) {
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid server ID"));

        // 멤버 권한 확인
        if (!server.getMember().getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("Unauthorized member ID");
        }

        // 서버 삭제
        serverRepository.delete(server);

        return ServerResponse.fromEntity(server);
    }

    private List<LocalDateTime> generateTimeSlots(LocalDateTime dateTime, ChronoUnit unit, int interval, int count) {
        List<LocalDateTime> timeSlots = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            timeSlots.add(dateTime.minus(i * interval, unit));
        }
        Collections.reverse(timeSlots); // 오름차순 정렬
        return timeSlots;
    }

    @Override
    public List<Long> monitorServer(Long serverId, LocalDateTime dateTime, ChronoUnit unit, int interval, int count) {
        Map<String, Long> requestCountsMap = new TreeMap<>();

        // 9시간 보정된 시간 생성
        LocalDateTime adjustedDateTime = dateTime.minusHours(9);

        // 시간 구간 생성
        List<LocalDateTime> timeSlots = generateTimeSlots(adjustedDateTime, unit, interval, count);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter esTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");

        // 인덱스는 dateTime의 날짜 기준으로 설정
        String indexDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String searchIndex = "server-logs-" + indexDate + "*";

        for (LocalDateTime timeSlot : timeSlots) {
            String formattedTime = timeSlot.plusHours(9).format(formatter);

            try {

                String gteTime = timeSlot.minus(interval, unit).format(esTimeFormatter);
                String ltTime = timeSlot.format(esTimeFormatter);

                // Elasticsearch 쿼리 설정
                SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                                .index(searchIndex)
                                .query(q -> q
                                        .range(r -> r
                                                .field("@timestamp")
                                                .gte(JsonData.of(gteTime))
                                                .lt(JsonData.of(ltTime))
                                        )
                                ),
                        Map.class
                );

                // 총 히트 수 가져오기
                long totalHits = searchResponse.hits().total().value();
                requestCountsMap.put(formattedTime, totalHits);

            } catch (Exception e) {
                if (e.getMessage().contains("index_not_found_exception")) {
                    requestCountsMap.put(formattedTime, 0L); // 인덱스가 없을 경우 0으로 설정
                } else {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(requestCountsMap);

        List<Long> countList = new ArrayList<>();

        for(String key: requestCountsMap.keySet()){
            countList.add(requestCountsMap.get(key));
        }
        return countList;
    }


    @Override
    public List<ThresholdResponse> getThresholds(Long memberId) {
        List<Server> servers = serverRepository.findByMember_MemberId(memberId);

        // 2. DTO로 변환하여 반환
        return servers.stream()
                .map(server -> ThresholdResponse.builder()
                        .serverId(server.getServerId())
                        .serverName(server.getServerName())
                        .serverLimit(server.getServerLimit())
                        .updatedAt(server.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CpuUsage getCPUData(Long containerId) throws IOException {
        // process Builder로 docker image stats 가져오기
        String[] commands = {"top", "-b", "-n", "1"}; // 이건 나중에 하드코딩 풀어야함.
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process result = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.getInputStream()));
        reader.readLine(); // 첫번째 줄 날리기
        String line;
        CpuUsage usage = new CpuUsage();
        boolean memoryCheck = false;
        while ((line = reader.readLine()) != null) {
            System.out.println("cur Line : " + line);
            if (line.contains("%Cpu(s):")) {
                // CPU 사용률 추출
                String[] cpuParts = line.split(",");
                String userCpu = cpuParts[0].split(":")[1].trim(); // us (사용자 모드 CPU)
                String sysCpu = cpuParts[1].trim();  // sy (시스템 모드 CPU)
                double userCpuPercent = Double.parseDouble(userCpu.split(" ")[0]);
                double sysCpuPercent = Double.parseDouble(sysCpu.split(" ")[0]);
                double cpuUsage = userCpuPercent + sysCpuPercent; // 전체 CPU 사용률
                System.out.println("user Cpu : " + userCpu + " sysCpu" + sysCpu);
                System.out.println(cpuUsage);
                usage.setCpuPercent(cpuUsage);

            }

            if (line.contains("MiB Mem :")) {
                // 메모리 사용량 추출
                String[] parts = line.split(":");
                String[] memParts = parts[1].split(",");
                // total과 free 값 추출
                String totalPart = memParts[0].trim(); // "15986.8 total"
                String usagePart = memParts[2].trim();  // "1186.5 free"
                System.out.println("total part : " + totalPart + " usagePart : " + usagePart);
                // total과 free 값에서 숫자만 추출
                double total = Double.parseDouble(totalPart.split(" ")[0].trim());
                double memuse = Double.parseDouble(usagePart.split(" ")[0].trim());
                System.out.println(total + " " + memuse);
                usage.setMemUsage(memuse);
                usage.setMemLimit(total);
                memoryCheck = true;
            }
            if (memoryCheck){
                break;
            }
        }
        return usage;
    }

    @Override
    public ServerMonthCostResponse monthServerCost(Long serverId, LocalDate localDate) {
        Map<String, Long> apiUsageMap = new TreeMap<>();

        // 오늘 날짜로 인덱스 설정
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy.MM"));
        String searchIndex = "server-logs-" + date + "*";

        System.out.println(searchIndex);

        try {
            SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                            .index(searchIndex)
                            .query(q -> q
                                    .matchPhrase(m -> m
                                            .field("message")
                                            .query("external_service: true")
                                    )
                            ),
                    Map.class
            );

            for (Hit<Map> hit : searchResponse.hits().hits()) {
                // 각 히트의 소스에서 `message` 필드를 가져옴
                Map<String, Object> sourceMap = hit.source();
                if (sourceMap != null && sourceMap.containsKey("message")) {
                    String message = sourceMap.get("message").toString();
                    System.out.println("Message: " + message);

                    // message에서 API 경로 추출하여 호출 횟수를 기록
                    String apiPath = extractApiPath(message);
                    apiUsageMap.put(apiPath, apiUsageMap.getOrDefault(apiPath, 0L) + 1);
                } else {
                    System.out.println("Message field is missing or source is null");
                }
            }

        } catch (Exception e) {
            if (e.getMessage().contains("index_not_found_exception")) {
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }

        Server server = serverRepository.findById(serverId).orElseThrow(()-> new NotFoundException("not found"));
        Instance instance = server.getInstance();

        double accumulatedCost = instance.getCostPerHour() * 24 * localDate.getDayOfMonth();
        // 인스턴스 누적 비용

        double expectedCost = instance.getCostPerHour() * 24 * 30;
        // 인스턴스 예상 월 비용

        double serviceCost = 0;

        List<Container> containerList = containerRepository.findContainersByServerId(server);
        for (int i = 0; i < containerList.size(); i++) {
            Container container = containerList.get(i);

            for (String api : apiUsageMap.keySet()) {
                ServiceUsage serviceUsage = serviceUsageRepository.findServiceUsageByServiceNameAndContainer(api, container);
                Long count = apiUsageMap.get(api);
                serviceCost += serviceUsage.getServiceCost() * count;
            }
        }


        accumulatedCost += serviceCost;
        expectedCost += (serviceCost / localDate.getDayOfMonth() )* 30;

        return new ServerMonthCostResponse(Double.parseDouble(String.format("%.3f", accumulatedCost))
                , Double.parseDouble(String.format("%.3f", expectedCost)));
    }

    @Override
    public ServerDailyCostResponse dailyServerCost(Long serverId, LocalDate localDate) {
        Map<String, Long> apiUsageMap = new TreeMap<>();

        // 오늘 날짜로 인덱스 설정
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String searchIndex = "server-logs-" + date + "*";

        try {
            SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                            .index(searchIndex)
                            .query(q -> q
                                    .matchPhrase(m -> m
                                            .field("message")
                                            .query("external_service: true")
                                    )
                            ),
                    Map.class
            );

            for (Hit<Map> hit : searchResponse.hits().hits()) {
                // 각 히트의 소스에서 `message` 필드를 가져옴
                Map<String, Object> sourceMap = hit.source();
                if (sourceMap != null && sourceMap.containsKey("message")) {
                    String message = sourceMap.get("message").toString();
                    System.out.println("Message: " + message);

                    // message에서 API 경로 추출하여 호출 횟수를 기록
                    String apiPath = extractApiPath(message);
                    apiUsageMap.put(apiPath, apiUsageMap.getOrDefault(apiPath, 0L) + 1);
                } else {
                    System.out.println("Message field is missing or source is null");
                }
            }

        } catch (Exception e) {
            if (e.getMessage().contains("index_not_found_exception")) {
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }

        Server server = serverRepository.findById(serverId).orElseThrow(()-> new NotFoundException("not found"));
        Instance instance = server.getInstance();

        double cost = instance.getCostPerHour() * 24;

        List<Container> containerList = containerRepository.findContainersByServerId(server);
        for (int i = 0; i < containerList.size(); i++) {
            Container container = containerList.get(i);

            for (String api : apiUsageMap.keySet()) {
                ServiceUsage serviceUsage = serviceUsageRepository.findServiceUsageByServiceNameAndContainer(api, container);
                Long count = apiUsageMap.get(api);
                cost += serviceUsage.getServiceCost() * count;
            }
        }


        return new ServerDailyCostResponse(Double.parseDouble(String.format("%.3f", cost)));
    }


    @Override
    public Map<String, Double> weeklyServerCost(Long serverId, LocalDate localDate) {
        Map<String, Double> dailyCosts = new TreeMap<>();

        // 요청 날짜로부터 일주일간의 날짜 설정
        LocalDate endDate = localDate;
        LocalDate startDate = endDate.minusDays(6);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            String searchIndex = "server-logs-" + formattedDate + "*";
            Map<String, Long> apiUsageMap = new TreeMap<>();

            try {
                // Elasticsearch 검색 요청
                SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s
                                .index(searchIndex)
                                .query(q -> q
                                        .matchPhrase(m -> m
                                                .field("message")
                                                .query("external_service: true")
                                        )
                                ),
                        Map.class
                );

                for (Hit<Map> hit : searchResponse.hits().hits()) {
                    Map<String, Object> sourceMap = hit.source();
                    if (sourceMap != null && sourceMap.containsKey("message")) {
                        String message = sourceMap.get("message").toString();
                        String apiPath = extractApiPath(message);
                        apiUsageMap.put(apiPath, apiUsageMap.getOrDefault(apiPath, 0L) + 1);
                    } else {
                        System.out.println("Message field is missing or source is null");
                    }
                }

            } catch (Exception e) {
                if (e.getMessage().contains("index_not_found_exception")) {
                    System.out.println("Index not found for date: " + formattedDate);
                } else {
                    e.printStackTrace();
                }
            }

            // 서버 인스턴스 비용 계산
            Server server = serverRepository.findById(serverId)
                    .orElseThrow(() -> new NotFoundException("Server not found"));

            Instance instance = server.getInstance();
            double dailyCost = instance.getCostPerHour() * 24;

            // API 사용 비용 계산
            // 서버 아이디로 컨테이너 여러개 가져와
            List<Container> containerList = containerRepository.findContainersByServerId(server);
            for (int i = 0; i < containerList.size(); i++) {
                Container container = containerList.get(i);

                for (String api : apiUsageMap.keySet()) {
                    ServiceUsage serviceUsage = serviceUsageRepository.findServiceUsageByServiceNameAndContainer(api, container);
                    Long count = apiUsageMap.get(api);
                    dailyCost += serviceUsage.getServiceCost() * count;
                }
            }


            // 날짜별 비용 저장 (소수점 3자리까지 반올림)
            dailyCosts.put(formattedDate, Double.parseDouble(String.format("%.3f", dailyCost)));
        }

        return dailyCosts;
    }

    @Override
    public CpuUsage getAllMemoryData(long serverId) throws IOException {
        String[] commands = {"docker","stats","--no-stream"};
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process result = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.getInputStream()));
        reader.readLine(); // 첫번째 줄 날리기
        String line;
        CpuUsage usage = new CpuUsage();
        double totalCpuUsage = 0;
        double totalMemoryUsage = 0;
        double totalMemoryLimit = 0;
        double numContainer = 0;
        while ((line = reader.readLine()) != null) {
            numContainer++;
            String[] containerData = line.trim().split("\\s+");

            String containerId = containerData[0];
            String name = containerData[1];
            double cpuUsage = Double.parseDouble(containerData[2].replace("%", ""));
            double memUsage = DockerStatsParser.parseMemory(Double.parseDouble(containerData[3].replaceAll("[^\\d.]", "")), containerData[3]);
            double memLimit = DockerStatsParser.parseMemory(Double.parseDouble(containerData[5].replaceAll("[^\\d.]", "")), containerData[5]);
            totalCpuUsage += cpuUsage;
            totalMemoryUsage += memUsage;
            totalMemoryLimit += memLimit;
        }
        if (numContainer == 0) return usage;

        usage.setMemUsage(totalMemoryUsage);
        usage.setMemLimit(totalMemoryLimit);
        usage.setCpuPercent(totalCpuUsage / numContainer);
        return usage;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveServerCPUUsage() throws IOException {
        // 5분마다 저장하기.
        // 서버마다 usage -> Elasticsearch에 저장
        List<Server> wholeServer = serverRepository.findAll(); // 모든 서버 가지고오기
        LocalDate cur = LocalDate.now();

        // 오늘 날짜로 인덱스 설정
        String date = cur.format(DateTimeFormatter.ofPattern("yyyy.MM"));
        String insertIndex = "cpu-usages-logs-" + date;

        System.out.println(insertIndex);
        for (Server s : wholeServer){
            CpuUsage usage = getAllMemoryData(s.getServerId());
            ElasitcsearchCpuUsageSaveRequest eUsage = ElasitcsearchCpuUsageSaveRequest.builder()
                    .serverId(s.getServerId())
                    .cpuPercent(usage.getCpuPercent())
                    .memPercent((usage.getMemUsage()/usage.getMemLimit()) * 100).build();

            try {
                IndexResponse response = elasticsearchClient.index(i->i.index(insertIndex)
                        .document(eUsage));

            } catch (Exception e) {
                if (e.getMessage().contains("index_not_found_exception")) {
                    e.printStackTrace();
                } else {
                    e.printStackTrace();
                }
            }
        }

    }


    // API 경로 추출 메서드
    private String extractApiPath(String message) {
        Pattern pattern = Pattern.compile("API: (/[^,]+)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "unknown";
    }

}
