package com.khacv.hotelbookingapp.repository.booking;

import com.khacv.hotelbookingapp.entity.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
       Booking findById(int id);

       List<Booking> findByBookingStatus(String status);
}
