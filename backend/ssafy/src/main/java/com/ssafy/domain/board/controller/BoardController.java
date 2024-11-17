package com.ssafy.domain.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final WebClient webClient;

    @Autowired
    public BoardController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://k11a606.p.ssafy.io:4041").build();
    }

    @GetMapping
    public ResponseEntity<?> getBoard() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "getBoard", "/boards", "GET", false, "ssafy-be");
        return ResponseEntity.ok("Get Request");
    }

    @PostMapping
    public ResponseEntity<?> createBoard() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "createBoard", "/boards", "POST", false, "ssafy-be");
        return ResponseEntity.ok("Post Request");
    }

    @GetMapping("/external-data/get")
    public Mono<String> getExternalData() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "getExternalData", "/external-data/get", "GET", true, "ssafy-be");
        return webClient.get()
                .uri("/api/get")
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/external-data/post")
    public Mono<String> postExternalData() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "postExternalData", "/external-data/post", "POST", true, "ssafy-be");
        return webClient.post()
                .uri("/api/post")
                .retrieve()
                .bodyToMono(String.class);
    }

}