package com.vn.cake_store.service;

import org.springframework.stereotype.Service;

import java.util.*;

import com.vn.cake_store.dto.request.CreateEmployeeRequest;
import com.vn.cake_store.dto.request.UpdateEmployeeRequest;
import com.vn.cake_store.entity.Employee;
import com.vn.cake_store.entity.Store;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
     private final EmployeeRepository employeeRepository;
     private final StoreService storeService;

     public Optional<Employee> findById(Long id) {
          var optionalEmployee = this.employeeRepository.findById(id);
          if (optionalEmployee.isEmpty()) {
               throw new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED);
          }
          return optionalEmployee;
     }

     public Employee createEmployee(CreateEmployeeRequest request) {

          Employee employee = new Employee();
          employee.setName(request.getName());
          employee.setRole(request.getRole());
          employee.setSalary(request.getSalary());

          Store store = new Store();
          if (request.getStoreId() != null) {
               store = this.storeService.findById(request.getStoreId()).get();
               employee.setStore(store);
          }

          return this.employeeRepository.save(employee);
     }

     public Employee getEmployeeById(Long id) {
          return this.findById(id).get();
     }

     public List<Employee> getAllEmployees() {
          return this.employeeRepository.findAll();
     }

     public Employee updateEmployee(UpdateEmployeeRequest request) {
          Employee dbEmployee = this.findById(request.getEmployeeId()).get();
          if (request.getStoreId() != null && request.getStoreId() != dbEmployee.getStore().getStoreId()) {
               var store = this.storeService.findById(request.getStoreId()).get();
               dbEmployee.setStore(store);
          }

          if (request.getName() != "" && request.getName() != null && request.getName() != dbEmployee.getName()) {
               dbEmployee.setName(request.getName());
          }
          if (request.getSalary() != null && request.getSalary() != dbEmployee.getSalary()) {
               dbEmployee.setSalary(request.getSalary());
          }
          if (request.getRole() != null && request.getRole() != dbEmployee.getRole()) {
               dbEmployee.setRole(request.getRole());
          }

          return this.employeeRepository.save(dbEmployee);
     }

     public void deleteEmployeeById(Long id) {
          this.employeeRepository.delete(this.findById(id).get());
     }
}
