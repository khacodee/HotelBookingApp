package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Booking;
import com.khacv.hotelbookingapp.entity.Guest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
       Booking findById(int id);

}
