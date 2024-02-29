package com.khacv.hotelbookingapp.service.reviews;

import com.khacv.hotelbookingapp.dto.review.ReviewDTO;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;

import java.util.List;

public interface IReviewsService {
    List<Reviews> getListReviews();

    Reviews getReviewsById(int id);

    Reviews createReviews(ReviewDTO reviewDTO);

    Reviews updateReviews(int id, ReviewDTO reviewDTO);

    Reviews deleteReviews(int id);
}
