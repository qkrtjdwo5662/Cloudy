package com.companyservice.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/sms/send")
    public ResponseEntity<?> sendSms(){
        log.info("{{\"method\":\"sendSms\", \"API\":\"/auth/sms/send\", \"http_method\":\"POST\", \"external_service\": true}}");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<?> verifySms(){
        log.info("{{\"method\":\"verifySms\", \"API\":\"/auth/sms/verify\", \"http_method\":\"POST\", \"external_service\": true}}");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(){
        log.info("{{\"method\":\"login\", \"API\":\"/auth/login\", \"http_method\":\"POST\", \"external_service\": false}}");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(){
        log.info("{{\"method\":\"register\", \"API\":\"/auth/register\", \"http_method\":\"POST\", \"external_service\": false}}");
        return ResponseEntity.ok("Post Request");
    }



}
