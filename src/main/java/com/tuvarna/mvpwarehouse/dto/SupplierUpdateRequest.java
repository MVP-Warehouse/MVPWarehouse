package com.tuvarna.mvpwarehouse.dto;

public class SupplierUpdateRequest {
    private String contactEmail;
    private String phone;

    public SupplierUpdateRequest() {
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
