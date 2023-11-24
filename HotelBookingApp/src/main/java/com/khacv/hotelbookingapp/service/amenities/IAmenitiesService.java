package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.dto.amenities.AmenitiesDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;

import java.util.List;

public interface IAmenitiesService {

    List<Amenities> getListAmenities();
    Amenities getAmenitiesById(int id);

    String addAmenities(AmenitiesDTO amenities);
    String updateAmenities(int id, AmenitiesDTO amenities);
    String deleteAmenities(int id);
}
