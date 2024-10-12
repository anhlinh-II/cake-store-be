package com.vn.cake_store.dto.request;

import com.vn.cake_store.entity.constants.Rating;

import lombok.Data;

@Data
public class CreateReviewRequest {
     private Long productId;

     private Long customerId;

     private Rating rating;

     private String comment;
}
