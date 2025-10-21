package com.tuvarna.mvpwarehouse.controller;

import com.tuvarna.mvpwarehouse.dto.HealthResponse;
import com.tuvarna.mvpwarehouse.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/api/health")
    public HealthResponse health() {
        return healthService.health();
    }
}
