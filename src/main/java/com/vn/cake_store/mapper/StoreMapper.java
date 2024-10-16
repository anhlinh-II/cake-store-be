package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.StoreResponse;
import com.vn.cake_store.entity.Store;

@Component
public class StoreMapper {
     public StoreResponse toStoreResponse(Store store) {
          StoreResponse storeResponse = new StoreResponse();
          storeResponse.setStoreId(store.getStoreId());
          storeResponse.setLocation(store.getLocation());
          storeResponse.setName(store.getName());
          storeResponse.setManagerId(store.getManager().getEmployeeId());

          return storeResponse;
     }
}
