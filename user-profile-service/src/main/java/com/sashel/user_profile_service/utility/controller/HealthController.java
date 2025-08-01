package com.sashel.user_profile_service.utility.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/userprofile/health")
public class HealthController {

    @GetMapping
    public String health() {
        return "Healthy";
    }

}
