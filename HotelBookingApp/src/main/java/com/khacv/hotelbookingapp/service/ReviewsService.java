package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Reviews;
import com.khacv.hotelbookingapp.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public List<Reviews> getListReviews(){
        return reviewsRepository.findAll();
    }

    public Reviews getReviewsById(int id){
        return reviewsRepository.findById(id);
    }
}
