package com.vn.cake_store.dto.request;

import com.vn.cake_store.entity.constants.Role;

import lombok.Data;

@Data
public class CreateEmployeeRequest {
     private String name;
     private Double salary;
     private Role role;
     private Long storeId;
}
