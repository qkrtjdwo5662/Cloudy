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
    public ResponseEntity<?> sendSms() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}",
                "sendSms", "/auth/sms/send", "POST", true);
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<?> verifySms() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}",
                "verifySms", "/auth/sms/verify", "POST", true);
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}",
                "login", "/auth/login", "POST", false);
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}",
                "register", "/auth/register", "POST", false);
        return ResponseEntity.ok("Post Request");
    }

}