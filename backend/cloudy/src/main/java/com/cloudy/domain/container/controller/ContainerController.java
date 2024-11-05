package com.cloudy.domain.container.controller;

import com.cloudy.domain.container.model.dto.request.ContainerGetUsagesRequest;
import com.cloudy.domain.container.service.ContainerService;
import com.cloudy.global.config.guard.Login;
import com.cloudy.global.config.swagger.SwaggerApiSuccess;
import com.cloudy.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨테이너 관련 API")
@RestController
@Slf4j
@RequestMapping("/containers")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    @Operation(summary = "서버 전체 컨테이너 사용량 조회 API", description = "서버 전체 컨테이너 사용량을 전체 조회합니다.")
    @SwaggerApiSuccess(description = "서버 전체 컨테이너 사용량 조회를 성공했습니다.")
    @GetMapping
    public Response<?> getContainerUsages(@Parameter(name = "서버 id", example = "1") @RequestParam Long serverId,
                                          @Login Long memberId,
                                          @Parameter(name = "시작일시", example = "2024-11-03 14:27:00") @RequestParam String startDateTime,
                                          @Parameter(name = "종료일시", example = "2024-11-04 14:27:00") @RequestParam String endDateTime) {
        containerService.getContainerUsages(new ContainerGetUsagesRequest(serverId, memberId, startDateTime, endDateTime));
        return Response.SUCCESS();
    }
}
