package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.EmployeeResponse;
import com.vn.cake_store.entity.Employee;

@Component
public class EmployeeMapper {
     public EmployeeResponse toEmployeeResponse (Employee employee) {
          EmployeeResponse response = new EmployeeResponse();
          response.setEmployeeId(employee.getEmployeeId());
          response.setName(employee.getName());
          response.setHireDate(employee.getHireDate());
          response.setRole(employee.getRole());
          response.setSalary(employee.getSalary());
          if (employee.getStore() != null) {
               response.setStoreId(employee.getStore().getStoreId());
          }

          return response;
     }
}
