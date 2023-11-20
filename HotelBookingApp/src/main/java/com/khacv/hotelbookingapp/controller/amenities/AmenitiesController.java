package com.khacv.hotelbookingapp.controller.amenities;

import com.khacv.hotelbookingapp.service.amenities.AmenitiesService;
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
public class AmenitiesController {

    @Autowired
    private AmenitiesService amenitiesService;

    @GetMapping("/amenities")
    public ResponseEntity<?> getListAmenities(){

        try
        {

            return ResponseEntity.ok(amenitiesService.getListAmenities());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR+ e.getMessage());
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR + e.getMessage());
        }
    }

}
