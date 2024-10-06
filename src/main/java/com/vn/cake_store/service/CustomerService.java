package com.vn.cake_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

     private final CustomerRepository customerRepository;

     public List<Customer> getAllCustomers() {
          return customerRepository.findAll();
     }

     public Optional<Customer> getCustomerById(Long id) {
          return customerRepository.findById(id);
     }

     public Customer createCustomer(Customer customer) {
          return customerRepository.save(customer);
     }

     public Customer updateCustomer(Long id, Customer customer) {
          customer.setCustomerId(id);
          return customerRepository.save(customer);
     }

     public void deleteCustomer(Long id) {
          customerRepository.deleteById(id);
     }
}
