package com.khacv.hotelbookingapp.controller.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.dto.booking.BookingWithGuestDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.booking.BookingService;
import com.khacv.hotelbookingapp.service.email.EmailService;
import com.khacv.hotelbookingapp.service.guest.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    @Autowired
    private GuestService guestService;
    @Autowired
    private EmailService emailService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings(@RequestParam(required = false) String status){
        ErrorResponse errorResponse = new ErrorResponse();
        ApiResponse<List<Booking>> response = new ApiResponse<>();
        try {
            List<Booking> bookings = (status != null && status.equalsIgnoreCase(PENDING))
                    ? bookingService.getListBookingApprove()
                    : bookingService.getListBooking();
            response.setData(bookings);
        }
        catch (Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_BOOKINGS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/booking/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id){
        ApiResponse<Booking> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            Booking booking = bookingService.getBookingById(id);
            response.setData(booking);
        } catch (Exception e){
           errorResponse.setCode(404); // Not Found
           errorResponse.setMessage("ERROR_BOOKING");
           List<String> errorMessages = new ArrayList<>();
           errorMessages.add(e.getMessage());
           errorResponse.setErrors(errorMessages);
           response.setError(errorResponse);
           return ResponseEntity.badRequest().body(response);
       }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/booking/{id}/approve")
    public ResponseEntity<ApiResponse<Booking>> confirmBooking(@PathVariable int id) {
            ApiResponse<Booking> response = new ApiResponse<>();

            try {

                Booking booking =  bookingService.approveBookRoom(id);

                emailService.sendApprovedEmail(id);


                response.setData(booking);
            }
            catch (Exception e){
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode(400);
                errorResponse.setMessage("ERROR_BOOKING");
                List<String> errorMessages = new ArrayList<>();
                errorMessages.add(e.getMessage());
                errorResponse.setErrors(errorMessages);
                response.setError(errorResponse);
                return ResponseEntity.badRequest().body(response);
            }
            return ResponseEntity.ok(response);
    }


    @PostMapping("/booking")
    public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO){
        ErrorResponse errorResponse = new ErrorResponse();
            ApiResponse<Booking> response = new ApiResponse<>();
            try {
                Booking createdBooking = bookingService.createBooking(bookingRoomDTO);
                response.setData(createdBooking);
            }
            catch (Exception e){
                errorResponse.setCode(400);
                errorResponse.setMessage("ERROR_BOOKING");
                List<String> errorMessages = new ArrayList<>();
                errorMessages.add(e.getMessage());
                errorResponse.setErrors(errorMessages);
                response.setError(errorResponse);
                return ResponseEntity.badRequest().body(response);
            }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/booking-with-guest")
    public ResponseEntity<?> createBookingRoomWithGuest(@RequestBody BookingWithGuestDTO bookingWithGuestDTO){
        ErrorResponse errorResponse = new ErrorResponse();
        ApiResponse<Guest> response = new ApiResponse<>();
        try {
            Guest createdBooking = bookingService.createBookingWithGuest(bookingWithGuestDTO);
            response.setData(createdBooking);
        }
        catch (Exception e){
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_BOOKING");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<?> updateBookingRoom (@PathVariable int id, @RequestBody BookingRoomDTO updateBooking){
        ApiResponse<Booking> response = new ApiResponse<>();
        try {
            Booking booking = bookingService.updateBooking(id, updateBooking);
            response.setData(booking);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_BOOKING");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PutMapping("/booking/{id}/reject")
    public ResponseEntity<ApiResponse<Booking>> rejectedBookRoom(@PathVariable int id){
        ApiResponse<Booking> response = new ApiResponse<>();
        try {
            Booking booking = bookingService.rejectedBookRoom(id);
            response.setData(booking);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_BOOKING");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id){
        ApiResponse<Booking> response = new ApiResponse<>();
        try {
            Booking booking = bookingService.deleteBooking(id);
            response.setData(booking);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_BOOKING");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
