package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.vn.cake_store.dto.request.CreateEmployeeRequest;
import com.vn.cake_store.dto.request.UpdateEmployeeRequest;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.EmployeeResponse;
import com.vn.cake_store.mapper.EmployeeMapper;
import com.vn.cake_store.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
     private final EmployeeService employeeService;
     private final EmployeeMapper employeeMapper;

     @PostMapping
     public ApiResponse<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest request) {
          return ApiResponse.<EmployeeResponse>builder()
                    .code(1000)
                    .message("Create an employee successfully!")
                    .result(employeeMapper.toEmployeeResponse(this.employeeService.createEmployee(request)))
                    .build();
     }

     @GetMapping("{id}")
     public ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
          return ApiResponse.<EmployeeResponse>builder()
                    .code(1000)
                    .message("Get employee with ID " + id + " successfully!")
                    .result(employeeMapper.toEmployeeResponse(this.employeeService.getEmployeeById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<EmployeeResponse>> getAll() {
          var allEmployees = this.employeeService.getAllEmployees();
          var allEmployeesRes = allEmployees.stream().map(employeeMapper::toEmployeeResponse).toList();
          return ApiResponse.<List<EmployeeResponse>>builder()
                    .code(1000)
                    .message("Get all employee successfully!")
                    .result(allEmployeesRes)
                    .build();
     }

     @PutMapping
     public ApiResponse<EmployeeResponse> updateEmployee(@RequestBody UpdateEmployeeRequest request) {
          return ApiResponse.<EmployeeResponse>builder()
                    .code(1000)
                    .message("Update employee with ID " + request.getEmployeeId() + " successfully!")
                    .result(employeeMapper.toEmployeeResponse(this.employeeService.updateEmployee(request)))
                    .build();
     }

     @DeleteMapping("{id}")
     public ApiResponse<Void> deleteEmployee(@PathVariable Long id) {
          this.employeeService.deleteEmployeeById(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete employee with ID " + id + " successfully!")
                    .build();
     }
}
