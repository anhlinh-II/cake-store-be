package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.ReviewResponse;
import com.vn.cake_store.entity.Review;

@Component
public class ReviewMapper {
     public ReviewResponse toReviewResponse (Review review) {
          ReviewResponse response = new ReviewResponse();
          response.setReviewId(review.getReviewId());
          response.setProductName(review.getProduct().getName());
          response.setCustomerName(review.getCustomer().getName());
          response.setComment(review.getComment());
          response.setRating(review.getRating());
          response.setCreatedAt(review.getCreatedAt());
          response.setUpdatedAt(review.getUpdatedAt());

          return response;
     }
}
