package com.khacv.hotelbookingapp.controller.reviews;

import com.khacv.hotelbookingapp.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ReviewsController {
    @Autowired
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getListReviews(){
        return ResponseEntity.ok(reviewsService.getListReviews());
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable int id){
        return ResponseEntity.ok(reviewsService.getReviewsById(id));
    }
}
