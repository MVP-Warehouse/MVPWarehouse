package com.tuvarna.mvpwarehouse.dto;

import java.time.OffsetDateTime;

public class SupplierResponse {
    private Long id;
    private String name;
    private String contactEmail;
    private String phone;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public SupplierResponse() {
    }

    public SupplierResponse(Long id, String name, String contactEmail, String phone,
                            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
