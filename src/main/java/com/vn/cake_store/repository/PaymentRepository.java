package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

     Payment findByOrder(Order order);
     
}
