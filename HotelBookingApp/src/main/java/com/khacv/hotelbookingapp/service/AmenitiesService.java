package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Amenities;
import com.khacv.hotelbookingapp.repository.AmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenitiesService {
    @Autowired
    private AmenitiesRepository amenitiesRepository;

    public List<Amenities> getListAmenities(){
        return amenitiesRepository.findAll();
    }

    public Amenities getAmenitiesById(int id){
        return amenitiesRepository.findById(id);
    }
}
