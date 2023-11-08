package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public ResponseEntity<?> getListBook(){
        return ResponseEntity.ok(bookingService.getListBooking());
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}
