package com.khacv.hotelbookingapp.controller.reviews;

import com.khacv.hotelbookingapp.dto.review.ReviewDTO;
import com.khacv.hotelbookingapp.entity.payment.Payment;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
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
        ApiResponse<List<Reviews>> response = new ApiResponse<>();
        try {
            List<Reviews> reviews = reviewsService.getListReviews();
            response.setData(reviews);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_REVIEWS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
            return ResponseEntity.ok(response);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable int id){
        ApiResponse<Reviews> response = new ApiResponse<>();
        try {
            Reviews reviews = reviewsService.getReviewsById(id);
            response.setData(reviews);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_REVIEWS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReviews(@RequestBody ReviewDTO reviewDTO){

        ApiResponse<Reviews> response = new ApiResponse<>();
        try {
    Reviews reviews = reviewsService.createReviews(reviewDTO);
    response.setData(reviews);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400); // Bad Request
            errorResponse.setMessage("ERROR_REVIEWS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<?> updateReviews(@PathVariable int id, @RequestBody ReviewDTO reviewDTO){
        ApiResponse<Reviews> response = new ApiResponse<>();
        try {
            Reviews reviews = reviewsService.updateReviews(id, reviewDTO);
            response.setData(reviews);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400); // Bad Request
            errorResponse.setMessage("ERROR_REVIEWS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReviews(@PathVariable int id){
        ApiResponse<Reviews> response = new ApiResponse<>();
        try {
            Reviews reviews = reviewsService.deleteReviews(id);
            response.setData(reviews);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400); // Bad Request
            errorResponse.setMessage("ERROR_REVIEWS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
