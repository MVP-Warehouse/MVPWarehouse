package com.tuvarna.mvpwarehouse.controller;

import com.tuvarna.mvpwarehouse.dto.HealthResponse;
import com.tuvarna.mvpwarehouse.service.IHealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final IHealthService IHealthService;

    public HealthController(IHealthService healthService) {
        this.IHealthService = healthService;
    }

    @GetMapping("/api/health")
    public HealthResponse health() {
        return IHealthService.health();
    }
}
