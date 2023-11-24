package com.khacv.hotelbookingapp.controller.amenities;

import com.khacv.hotelbookingapp.dto.amenities.AmenitiesDTO;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.amenities.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class AmenitiesController {

    @Autowired
    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    @GetMapping("/amenities")
    public ResponseEntity<?> getListAmenities(){

        try
        {

            return ResponseEntity.ok(amenitiesService.getListAmenities());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/amenities/{id}")
    public ResponseEntity<?> getAmenitiesById(@PathVariable int id){
        try
        {
        return ResponseEntity.ok(amenitiesService.getAmenitiesById(id));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PostMapping("/amenities")
    public ResponseEntity<?> createAmenities(@RequestBody AmenitiesDTO amenitiesDTO){
        try
        {
        return ResponseEntity.ok(amenitiesService.addAmenities(amenitiesDTO));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/amenities/{id}")
    public ResponseEntity<?> updateAmenities(@PathVariable int id, @RequestBody AmenitiesDTO amenitiesDTO){
        try
        {
        return ResponseEntity.ok(amenitiesService.updateAmenities(id, amenitiesDTO));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

