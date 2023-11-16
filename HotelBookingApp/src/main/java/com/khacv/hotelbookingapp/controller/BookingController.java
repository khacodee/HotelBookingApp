package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.dto.BookingRoomDTO;
import com.khacv.hotelbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/booking")
    public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO){
        return ResponseEntity.ok(bookingService.createBooking(bookingRoomDTO));
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<?> approveBookRoom(@PathVariable int id,@RequestParam("status") String status){
        return ResponseEntity.ok(bookingService.approveBookRoom(id, status));
    }

}
