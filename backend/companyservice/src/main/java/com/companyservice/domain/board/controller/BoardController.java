package com.companyservice.domain.board.controller;

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
    public ResponseEntity<?> getBoard(){
        log.info("{{\"method\":\"getBoard\", \"API\":\"/boards\", \"http_method\":\"GET\", \"external_service\": false}}");
        return ResponseEntity.ok("Get Request");
    }

    @PostMapping
    public ResponseEntity<?> createBoard(){
        log.info("{{\"method\":\"createBoard\", \"API\":\"/boards\", \"http_method\":\"POST\", \"external_service\": false}}");
        return ResponseEntity.ok("Post Request");
    }

    @GetMapping("/external-data/get")
    public Mono<String> getExternalData() {
        log.info("{{\"method\":\"getExternalData\", \"API\":\"/external-data/get\", \"http_method\":\"GET\", \"external_service\": true}}");
        return webClient.get()
                .uri("/api/get")
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/external-data/post")
    public Mono<String> postExternalData() {
        log.info("{{\"method\":\"postExternalData\", \"API\":\"/external-data/post\", \"http_method\":\"POST\", \"external_service\": true}}");
        return webClient.get()
                .uri("/api/post")
                .retrieve()
                .bodyToMono(String.class);
    }

}