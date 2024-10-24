package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.CustomerResponse;
import com.vn.cake_store.entity.Customer;

@Component
public class CustomerMapper {
     public CustomerResponse toCustomerResponse (Customer customer) {
          CustomerResponse response = new CustomerResponse();
          response.setCustomerId(customer.getCustomerId());
          response.setAddress(customer.getAddress());
          response.setEmail(customer.getEmail());
          response.setName(customer.getName());
          response.setPhone(customer.getPhone());

          return response;
     }
}
