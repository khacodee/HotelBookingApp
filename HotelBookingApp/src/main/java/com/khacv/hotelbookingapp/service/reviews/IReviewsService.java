package com.khacv.hotelbookingapp.service.reviews;

import com.khacv.hotelbookingapp.dto.review.ReviewDTO;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;

import java.util.List;

public interface IReviewsService {
    List<Reviews> getListReviews();

    Reviews getReviewsById(int id);

    String createReviews(ReviewDTO reviewDTO);

    String updateReviews(int id, ReviewDTO reviewDTO);

    String deleteReviews(int id);
}
