package com.cloudy.domain.server.service;

import co.elastic.clients.elasticsearch.nodes.Cpu;
import com.cloudy.domain.instance.model.Instance;
import com.cloudy.domain.instance.repository.InstanceRepository;
import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.repository.MemberRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.server.model.dto.request.ServerCreateRequest;
import com.cloudy.domain.server.model.dto.request.ServerUpdateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdCreateRequest;
import com.cloudy.domain.server.model.dto.request.ThresholdUpdateRequest;
import com.cloudy.domain.server.model.dto.response.*;
import com.cloudy.domain.server.repository.ServerRepository;
import com.cloudy.global.util.DockerStatsParser;
import jakarta.transaction.Transactional;
import jdk.jfr.Threshold;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final MemberRepository memberRepository;
    private final InstanceRepository instanceRepository;

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
        List<Server> servers = serverRepository.findByMember_MemberId(memberId);

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

    // todo: 얘는 나중에 데이터 넣어보고 구현해봐야됨
    @Override
    public MonitoringResponse monitorServer(Long serverId, int duration) {



        return null;
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
        String[] commands = {"top","-n","1"}; // 이건 나중에 하드코딩 풀어야함.

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process result = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.getInputStream()));
        reader.readLine(); // 첫번째 줄 날리기
        String line;
        CpuUsage usage = new CpuUsage();
        while ((line = reader.readLine()) != null) {
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
                String[] memParts = line.split(",");
                // total과 free 값 추출
                String totalPart = memParts[0].trim(); // "15986.8 total"
                String usagePart = memParts[2].trim();  // "1186.5 free"

                // total과 free 값에서 숫자만 추출
                double total = Double.parseDouble(totalPart.split(" ")[0].trim());
                double memuse = Double.parseDouble(usagePart.split(" ")[0].trim());
                System.out.println("total part" + totalPart + " usagePart" + usagePart);
                System.out.println(total + " " + memuse);
                usage.setMemUsage(memuse);
                usage.setMemLimit(total);
            }
        }
        return usage;
    }



}
