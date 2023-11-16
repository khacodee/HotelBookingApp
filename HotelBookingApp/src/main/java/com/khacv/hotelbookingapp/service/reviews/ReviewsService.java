package com.khacv.hotelbookingapp.service.reviews;

import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import com.khacv.hotelbookingapp.repository.reviews.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements IReviewsService{
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<Reviews> getListReviews(){
        return reviewsRepository.findAll();
    }

    @Override
    public Reviews getReviewsById(int id){
        return reviewsRepository.findById(id);
    }
}
