package com.vn.cake_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.CustomerResponse;
import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.mapper.CustomerMapper;
import com.vn.cake_store.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

     private final CustomerRepository customerRepository;
     private final CustomerMapper customerMapper;

     public ApiResponse<Page<CustomerResponse>> getAllCustomers(int page, int size) {
          Pageable pageable = PageRequest.of(page, size);
          Page<Customer> customers = customerRepository.findAll(pageable);
          Page<CustomerResponse> allResCustomers = customers.map(customerMapper::toCustomerResponse);
          return ApiResponse.<Page<CustomerResponse>>builder()
                    .code(1000)
                    .result(allResCustomers)
                    .message("Get all users successfully!")
                    .build();
     }

     public Optional<Customer> findById(Long id) {
          return customerRepository.findById(id);
     }

     public ApiResponse<CustomerResponse> getCustomerById(Long id) {
          Optional<Customer> optionalCustomer = this.findById(id);
          if (optionalCustomer == null) {
               throw new AppException(ErrorCode.USER_NOT_EXISTED);
          }
          Customer customer = optionalCustomer.get();
          CustomerResponse response = customerMapper.toCustomerResponse(customer);
          return ApiResponse.<CustomerResponse>builder()
                    .code(1000)
                    .message("Get User Successfully!")
                    .result(response)
                    .build();
     }

     public ApiResponse<Customer> createCustomer(Customer customer) {
          Optional<Customer> optionalCustomer = this.customerRepository.findByName(customer.getName());
          if (optionalCustomer.isPresent()) {
               throw new AppException(ErrorCode.USER_EXISTED);
          }
          this.customerRepository.save(customer);
          return ApiResponse.<Customer>builder()
                    .code(1000)
                    .message("Create customer successfully")
                    .result(customer)
                    .build();
     }

     public ApiResponse<CustomerResponse> updateCustomer(Long id, Customer customer) {
          // Find the customer by ID
          var optionalCustomer = this.findById(id);

          // Check if the customer exists
          if (optionalCustomer.isEmpty()) {
               throw new AppException(ErrorCode.USER_NOT_EXISTED);
          }

          // Get the customer from the database
          var dbCustomer = optionalCustomer.get();

          // Update the customer details
          dbCustomer.setName(customer.getName());
          dbCustomer.setAddress(customer.getAddress());
          dbCustomer.setEmail(customer.getEmail());
          dbCustomer.setPhone(customer.getPhone());

          // Save the updated customer to the database
          var response = customerMapper.toCustomerResponse(customerRepository.save(dbCustomer));

          // Return the updated customer in the response
          return ApiResponse.<CustomerResponse>builder()
                    .code(1000)
                    .message("Update user successfully!")
                    .result(response) // Return the updated dbCustomer
                    .build();
     }

     public ApiResponse<Void> deleteCustomer(Long id) {
          this.customerRepository.delete(this.findById(id).get());
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete customer with id " + id + " succesfully!")
                    .result(null)
                    .build();
     }
}
