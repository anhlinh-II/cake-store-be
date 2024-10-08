package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.OrderDetails;
import com.vn.cake_store.entity.Product;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
     boolean existsByProductAndOrder(Product product, Order order);
}
