package com.vn.cake_store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

     Page<Review> findAll(Pageable pageable);

     boolean existsByProductAndCustomer(Product product, Customer customer);

     Page<Review> findAllByProduct(Product product, Pageable pageable);
     
}
