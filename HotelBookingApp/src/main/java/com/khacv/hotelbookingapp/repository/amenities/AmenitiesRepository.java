package com.khacv.hotelbookingapp.repository.amenities;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {

    Amenities findById(int id);
}
