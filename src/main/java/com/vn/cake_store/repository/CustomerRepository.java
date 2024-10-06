package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.cake_store.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     Optional<Customer> findByName(String name);
}
