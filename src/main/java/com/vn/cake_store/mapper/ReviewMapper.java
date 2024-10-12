package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.ReviewResponse;
import com.vn.cake_store.entity.Review;

@Component
public class ReviewMapper {
     public ReviewResponse toReviewResponse (Review review) {
          ReviewResponse response = new ReviewResponse();
          response.setReviewId(review.getReviewId());
          response.setProductId(review.getProduct().getProductId());
          response.setCustomerId(review.getCustomer().getCustomerId());
          response.setComment(review.getComment());
          response.setRating(review.getRating());
          response.setCreatedAt(review.getCreatedAt());
          response.setUpdatedAt(review.getUpdatedAt());

          return response;
     }
}
