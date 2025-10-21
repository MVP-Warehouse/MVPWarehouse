package com.tuvarna.mvpwarehouse.dto;

public class HealthResponse {

    private String status;
    private long timestamp;

    public HealthResponse() {
    }

    public HealthResponse(String status, long timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
