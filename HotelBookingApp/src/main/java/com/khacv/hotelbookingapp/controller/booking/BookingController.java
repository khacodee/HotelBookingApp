package com.khacv.hotelbookingapp.controller.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings(@RequestParam(required = false) String status){
        if(status != null && status.equalsIgnoreCase("PENDING")) {
            return ResponseEntity.ok(bookingService.getListBookingApprove());
        } else {
            return ResponseEntity.ok(bookingService.getListBooking());
        }
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
