package com.vn.cake_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cake_store.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     
}
