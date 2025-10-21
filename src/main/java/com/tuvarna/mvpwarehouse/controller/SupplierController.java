package com.tuvarna.mvpwarehouse.controller;

import com.tuvarna.mvpwarehouse.dto.SupplierCreateRequest;
import com.tuvarna.mvpwarehouse.dto.SupplierResponse;
import com.tuvarna.mvpwarehouse.dto.SupplierUpdateRequest;
import com.tuvarna.mvpwarehouse.service.ISupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final ISupplierService service;

    public SupplierController(ISupplierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody SupplierCreateRequest request) {
        SupplierResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/suppliers/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public SupplierResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SupplierResponse> list() {
        return service.list();
    }

    @PatchMapping("/{id}")
    public SupplierResponse update(@PathVariable Long id, @RequestBody SupplierUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
