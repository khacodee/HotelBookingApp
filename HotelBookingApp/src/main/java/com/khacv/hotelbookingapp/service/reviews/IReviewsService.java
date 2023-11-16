package com.khacv.hotelbookingapp.service.reviews;

import com.khacv.hotelbookingapp.entity.reviews.Reviews;

import java.util.List;

public interface IReviewsService {
    List<Reviews> getListReviews();

    Reviews getReviewsById(int id);
}
