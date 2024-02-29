package com.khacv.hotelbookingapp.service.amenities;

import com.khacv.hotelbookingapp.dto.amenities.AmenitiesDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.amenities.AmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AmenitiesService implements IAmenitiesService {
    @Autowired
    private AmenitiesRepository amenitiesRepository;

    @Cacheable(cacheNames = "amenities", key = "'all'")
    @Override
    public List<Amenities> getListAmenities() {
        return amenitiesRepository.findAll();
    }

    //@Cacheable(cacheNames = "amenities1", key = "#id")
    @Override
    public Amenities getAmenitiesById(int id) {
        Amenities amenities = amenitiesRepository.findById(id);
        if(amenities == null){
            throw new NotFoundException("Not found");
        }
        return amenities;
    }



    @Override
    public Amenities updateAmenities(int id, AmenitiesDTO amenitiesDTO) {
        Amenities amenities = amenitiesRepository.findById(id);
        if (amenities != null) {
            amenities.setName(amenitiesDTO.getName());
            amenitiesRepository.save(amenities);
        }
        return amenities;
    }

    @Override
    public Amenities deleteAmenities(int id) {
        Amenities amenities = amenitiesRepository.findById(id);
        if (amenities != null) {
            amenitiesRepository.delete(amenities);
        }
        return amenities;
    }

    @Override
    public Amenities createAmenities(AmenitiesDTO amenitiesDTO) {
        Amenities amenities = new Amenities();
        amenities.setName(amenitiesDTO.getName());
        amenitiesRepository.save(amenities);
        return amenities;
    }
}

