package com.khacv.hotelbookingapp.controller.reviews;

import com.khacv.hotelbookingapp.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.khacv.hotelbookingapp.util.Messages.*;

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
        try {
        return ResponseEntity.ok(reviewsService.getListReviews());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable int id){
        try {
        return ResponseEntity.ok(reviewsService.getReviewsById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }
}
