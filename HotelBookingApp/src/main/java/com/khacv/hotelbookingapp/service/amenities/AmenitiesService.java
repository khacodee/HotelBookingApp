package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.repository.amenities.AmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenitiesService implements IAmenitiesService {
    @Autowired
    private AmenitiesRepository amenitiesRepository;

    @Override
    public List<Amenities> getListAmenities(){
        return amenitiesRepository.findAll();
    }

    @Override
    public Amenities getAmenitiesById(int id){
        return amenitiesRepository.findById(id);
    }
}
