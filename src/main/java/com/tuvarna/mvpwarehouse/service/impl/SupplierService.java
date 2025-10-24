package com.tuvarna.mvpwarehouse.service.impl;

import com.tuvarna.mvpwarehouse.dto.SupplierCreateRequest;
import com.tuvarna.mvpwarehouse.dto.SupplierResponse;
import com.tuvarna.mvpwarehouse.dto.SupplierUpdateRequest;
import com.tuvarna.mvpwarehouse.exception.NotFoundException;
import com.tuvarna.mvpwarehouse.model.Supplier;
import com.tuvarna.mvpwarehouse.repository.ISupplierRepository;
import com.tuvarna.mvpwarehouse.service.ISupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierService implements ISupplierService {

    private final ISupplierRepository repository;

    public SupplierService(ISupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierResponse create(SupplierCreateRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Supplier name is required");
        }

        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Supplier with the same name already exists");
        }

        Supplier supplierToSave = new Supplier(request.getName(), request.getContactEmail(), request.getPhone());
        Supplier saved = repository.save(supplierToSave);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse getById(Long id) {
        Supplier s = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found: " + id));
        return toResponse(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public SupplierResponse update(Long id, SupplierUpdateRequest request) {
        Supplier s = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found: " + id));
        if (request.getContactEmail() != null) s.setContactEmail(request.getContactEmail());
        if (request.getPhone() != null) s.setPhone(request.getPhone());
        return toResponse(repository.save(s));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Supplier not found: " + id);
        }
        repository.deleteById(id);
    }

    private SupplierResponse toResponse(Supplier s) {
        return new SupplierResponse(
                s.getId(), s.getName(), s.getContactEmail(), s.getPhone(),
                s.getCreatedAt(), s.getUpdatedAt()
        );
    }
}
