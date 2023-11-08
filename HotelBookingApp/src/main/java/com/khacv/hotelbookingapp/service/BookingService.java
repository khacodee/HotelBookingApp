package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Booking;
import com.khacv.hotelbookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    public Booking getBookingById(int id){
        return bookingRepository.findById(id);
    }
}
