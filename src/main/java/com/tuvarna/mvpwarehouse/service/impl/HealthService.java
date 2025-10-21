package com.tuvarna.mvpwarehouse.service.impl;

import com.tuvarna.mvpwarehouse.dto.HealthResponse;
import com.tuvarna.mvpwarehouse.service.IHealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthService implements IHealthService {

    @Override
    public HealthResponse health() {
        return new HealthResponse("SERVER IS RUNNING", System.currentTimeMillis());
    }
}
