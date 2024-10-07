package com.vn.cake_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

     List<Order> findAllByCustomer(Customer customer);
     
}
