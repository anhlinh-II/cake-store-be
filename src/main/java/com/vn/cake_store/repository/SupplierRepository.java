package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

     boolean existsByNameOrEmail(String name, String email);
     
}
