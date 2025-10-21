package com.tuvarna.mvpwarehouse.service;

import com.tuvarna.mvpwarehouse.dto.SupplierCreateRequest;
import com.tuvarna.mvpwarehouse.dto.SupplierResponse;
import com.tuvarna.mvpwarehouse.dto.SupplierUpdateRequest;

import java.util.List;

public interface SupplierService {
    SupplierResponse create(SupplierCreateRequest request);

    SupplierResponse getById(Long id);

    List<SupplierResponse> list();

    SupplierResponse update(Long id, SupplierUpdateRequest request);

    void delete(Long id);
}
