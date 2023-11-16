package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;

import java.util.List;

public interface IAmenitiesService {

    List<Amenities> getListAmenities();
    Amenities getAmenitiesById(int id);
}
