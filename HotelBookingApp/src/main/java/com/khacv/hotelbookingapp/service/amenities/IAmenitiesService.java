package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.dto.amenities.AmenitiesDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAmenitiesService {

    List<Amenities> getListAmenities();
    Amenities getAmenitiesById(int id);

    Amenities createAmenities(AmenitiesDTO amenitiesDTO);

    Amenities updateAmenities(int id, AmenitiesDTO amenitiesDTO);

    Amenities deleteAmenities(int id);
}
