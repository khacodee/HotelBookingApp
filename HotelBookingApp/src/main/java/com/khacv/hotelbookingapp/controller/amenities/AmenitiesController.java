package com.khacv.hotelbookingapp.controller.amenities;

import com.khacv.hotelbookingapp.dto.amenities.AmenitiesDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.service.amenities.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class AmenitiesController {

    @Autowired
    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    @GetMapping("/amenities")
    public ResponseEntity<ApiResponse<List<Amenities>>> getListAmenities() {
        ApiResponse<List<Amenities>> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            List<Amenities> amenities = amenitiesService.getListAmenities();
            response.setData(amenities);
        }catch(Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_AMENITIES");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amenities/{id}")
    public ResponseEntity<ApiResponse<Amenities>> getAmenitiesById(@PathVariable int id) {
        ApiResponse<Amenities> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
        Amenities amenities = amenitiesService.getAmenitiesById(id);
        response.setData(amenities);
        }catch(Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_AMENITIES");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/amenities")
    public ResponseEntity<ApiResponse<Amenities>> createAmenities(@RequestBody AmenitiesDTO amenitiesDTO) {
        ApiResponse<Amenities> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            Amenities amenities = amenitiesService.createAmenities(amenitiesDTO);
            response.setData(amenities);
        }catch(Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_AMENITIES_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/amenities/{id}")
    public ResponseEntity<ApiResponse<Amenities>> updateAmenities(@PathVariable int id, @RequestBody AmenitiesDTO amenitiesDTO) {
        ApiResponse<Amenities> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            Amenities amenities = amenitiesService.updateAmenities(id, amenitiesDTO);
            response.setData(amenities);
        }catch(Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_AMENITIES_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/amenities/{id}")
    public ResponseEntity<ApiResponse<Amenities>> deleteAmenities(@PathVariable int id) {
        ApiResponse<Amenities> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            Amenities amenities = amenitiesService.deleteAmenities(id);
            response.setData(amenities);
        }catch(Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_AMENITIES_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}

