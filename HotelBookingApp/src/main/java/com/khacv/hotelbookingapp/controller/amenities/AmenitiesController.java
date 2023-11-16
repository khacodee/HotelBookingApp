package com.khacv.hotelbookingapp.controller.amenities;

import com.khacv.hotelbookingapp.service.amenities.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AmenitiesController {

    @Autowired
    private AmenitiesService amenitiesService;

    @GetMapping("/amenities")
    public ResponseEntity<?> getListAmenities(){
        return ResponseEntity.ok(amenitiesService.getListAmenities());
    }

    @GetMapping("/amenities/{id}")
    public ResponseEntity<?> getAmenitiesById(@PathVariable int id){
        return ResponseEntity.ok(amenitiesService.getAmenitiesById(id));
    }

}
