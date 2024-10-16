package com.vn.cake_store.dto.response;

import lombok.Data;

@Data
public class StoreResponse {
     private Long storeId;
     private String name;
     private Long managerId;
     private String location;
}
