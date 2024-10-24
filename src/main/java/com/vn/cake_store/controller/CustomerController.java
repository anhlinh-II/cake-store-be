package com.vn.cake_store.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.CustomerResponse;
import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
     public ApiResponse<Page<CustomerResponse>> getAllCustomers(
        @RequestParam(defaultValue = "0") int page,  // Default to page 0
        @RequestParam(defaultValue = "10") int size  // Default to 10 items per page
    ) {
        return customerService.getAllCustomers(page, size);
    }

    @GetMapping("/{id}")
    public ApiResponse<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ApiResponse<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ApiResponse<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCustomer(@PathVariable Long id) {
        
        return customerService.deleteCustomer(id);
    }
}
