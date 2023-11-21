package com.khacv.hotelbookingapp.controller.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings(@RequestParam(required = false) String status){
        try {
            if(status != null && status.equalsIgnoreCase(PENDING)) {
            return ResponseEntity.ok(bookingService.getListBookingApprove());
        } else {
            return ResponseEntity.ok(bookingService.getListBooking());
        }
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }


    @GetMapping("/booking/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id){
        try {
        return ResponseEntity.ok(bookingService.getBookingById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }


    @PostMapping("/booking")
    public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO){
        try {
            return ResponseEntity.ok(bookingService.createBooking(bookingRoomDTO));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<?> updateBookingRoom (@PathVariable int id, @RequestBody BookingRoomDTO updateBooking){
        try {
            return ResponseEntity.ok(bookingService.updateBooking(id, updateBooking));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @PutMapping("/booking/{id}/approve")
    public ResponseEntity<?> approveBookRoom(@PathVariable int id){
        try {
            return ResponseEntity.ok(bookingService.approveBookRoom(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @PutMapping("/booking/{id}/reject")
    public ResponseEntity<?> rejectedBookRoom(@PathVariable int id){
        try {
            return ResponseEntity.ok(bookingService.rejectedBookRoom(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }


}
