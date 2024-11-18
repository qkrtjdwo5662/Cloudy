package com.companyservice.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "sendSms", "KAKAO", "POST", true, "company");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<?> verifySms() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "verifySms", "PAYCO", "POST", true, "company");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "login", "/auth/login", "POST", false, "company");
        return ResponseEntity.ok("Post Request");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register() {
        log.info("method: {}, API: {}, http_method: {}, external_service: {}, container: {}",
                "register", "/auth/register", "POST", false, "company");
        return ResponseEntity.ok("Post Request");
    }

}
