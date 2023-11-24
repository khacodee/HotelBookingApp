package com.khacv.hotelbookingapp.controller.reviews;

import com.khacv.hotelbookingapp.dto.review.ReviewDTO;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable int id){
        try {
        return ResponseEntity.ok(reviewsService.getReviewsById(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReviews(@RequestBody ReviewDTO reviewDTO){
        try {
        return ResponseEntity.ok(reviewsService.createReviews(reviewDTO));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<?> updateReviews(@PathVariable int id, @RequestBody ReviewDTO reviewDTO){
        try {
        return ResponseEntity.ok(reviewsService.updateReviews(id, reviewDTO));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReviews(@PathVariable int id){
        try {
        return ResponseEntity.ok(reviewsService.deleteReviews(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
