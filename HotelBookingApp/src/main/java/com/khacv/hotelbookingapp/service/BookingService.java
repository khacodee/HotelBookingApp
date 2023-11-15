package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Booking;
import com.khacv.hotelbookingapp.entity.Payment;
import com.khacv.hotelbookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    public Booking getBookingById(int id){

        Booking booking = bookingRepository.findById(id);

//        Set<Payment> payments =booking.getPayments();
//        if(payments.isEmpty()){
//            System.out.println("Danh sách thanh toán của đặt phòng rỗng");
//        }

        return  booking;
    }
}
