package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Employee;
import com.vn.cake_store.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

     boolean existsByName(String name);

     boolean existsByManager(Employee employee);
     
}
