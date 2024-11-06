package com.externalservice.domain.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("/get")
    public ResponseEntity<?> getAPI(){
        return ResponseEntity.ok("external get API");
    }

    @PostMapping("/post")
    public ResponseEntity<?> postAPI(){
        return ResponseEntity.ok("external post API");
    }

}
