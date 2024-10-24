package com.vn.cake_store.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long customerId;

    private String name;

    private String email;

    private String phone;
    private String address;
}
