package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
       Booking findById(int id);
}
