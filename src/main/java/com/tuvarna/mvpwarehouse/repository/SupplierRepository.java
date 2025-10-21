package com.tuvarna.mvpwarehouse.repository;

import com.tuvarna.mvpwarehouse.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByNameIgnoreCase(String name);
}
