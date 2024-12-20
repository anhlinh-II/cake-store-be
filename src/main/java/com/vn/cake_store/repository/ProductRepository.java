package com.vn.cake_store.repository;

import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     Optional<Product> findByName(String name);

     Page<Product> findAll(Pageable pageable);

     List<Product> findByProductIdIn(List<Long> idsList);

     boolean existsByName(String name);
     
}
