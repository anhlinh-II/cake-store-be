package com.vn.cake_store.dto.request;

import com.vn.cake_store.entity.constants.Rating;

import lombok.Data;

@Data
public class UpdateReviewRequest {
     private Long reviewId;
     private Rating rating;
     private String comment;
}
