package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {

    Amenities findById(int id);
}
