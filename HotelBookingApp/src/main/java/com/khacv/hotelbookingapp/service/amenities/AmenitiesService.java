package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.amenities.AmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

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
        Amenities amenities = amenitiesRepository.findById(id);

        if(amenities == null){
            throw new NotFoundException(NOT_FOUND);
        }
        return amenities;
    }
}
