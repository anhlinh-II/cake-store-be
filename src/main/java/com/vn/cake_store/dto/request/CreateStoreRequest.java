package com.vn.cake_store.dto.request;

import lombok.Data;

@Data
public class CreateStoreRequest {
     private String name;
     private String location;
     private Long managerId;
}
