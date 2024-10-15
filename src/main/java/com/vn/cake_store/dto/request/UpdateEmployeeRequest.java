package com.vn.cake_store.dto.request;

import com.vn.cake_store.entity.constants.Role;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
     private Long employeeId;
     private String name;
     private Double salary;
     private Role role;
     private Long storeId;
}
