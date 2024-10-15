package com.vn.cake_store.dto.response;

import java.time.Instant;

import com.vn.cake_store.entity.constants.Rating;

import lombok.Data;

@Data
public class ReviewResponse {
     private Long reviewId;

     private Long productId;

     private Long customerId;

     private Rating rating;

     private String comment;

     private Instant createdAt;

     private Instant updatedAt;
}