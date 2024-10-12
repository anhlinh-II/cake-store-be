package com.vn.cake_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

     boolean existsByProductAndCustomer(Product product, Customer customer);

     List<Review> findAllByProduct(Product product);
     
}
