package com.vn.cake_store.dto.response;

import com.vn.cake_store.entity.constants.Role;

import lombok.Data;

import java.time.*;

@Data
public class EmployeeResponse {
     private Long employeeId;
     private String name;
     private Role role;
     private Double salary;
     private Instant hireDate;
     private Long storeId;
}
