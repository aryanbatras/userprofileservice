package com.sashel.user_profile_service.utility.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/userprofile/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Healthy");
    }

}
