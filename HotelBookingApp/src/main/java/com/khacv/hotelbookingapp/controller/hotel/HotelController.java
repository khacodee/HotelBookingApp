package com.khacv.hotelbookingapp.controller.hotel;


import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.dto.hotel.HotelWithAmenitiesDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Constants.PENDING;
import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class HotelController {
    @Autowired
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> getListHotel(@RequestParam(required = false) boolean isActive){
        ApiResponse<List<Hotel>> response = new ApiResponse<>();
        try {
            List<Hotel> hotels = (isActive == true)
                    ? hotelService.getListHotelIsActive()
                    : hotelService.getListHotel();
            response.setData(hotels);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }



    @GetMapping("/hotels/{id}")
    public ResponseEntity<ApiResponse<Hotel>> getHotelWithRooms(@PathVariable int id, @RequestParam(required = false) boolean status) {
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = (status != true)
                    ? hotelService.getHotelById(id)
                    : hotelService.getHotelByIdWithRoom(id);
            response.setData(hotel);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }



    @PostMapping("/hotels")
    public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO){
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = hotelService.createHotel(hotelDTO);
            response.setData(hotel);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<?> updateHotel( @PathVariable int id,@RequestBody HotelDTO hotelDTO){
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = hotelService.updateHotel(id,hotelDTO);
            response.setData(hotel);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id){
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = hotelService.deleteHotel(id);
            response.setData(hotel);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/hotels/{id}/active")
    public ResponseEntity<?> ActiveHotel(@PathVariable int id){
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = hotelService.ActiveHotel(id);
            response.setData(hotel);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/hotels/create-with-amenities")
    public ResponseEntity<?> createHotelWithAmenities(@RequestBody HotelWithAmenitiesDTO hotelWithAmenitiesDTO) {
        ApiResponse<Hotel> response = new ApiResponse<>();
        try {
            Hotel hotel = hotelService.createHotelWithAmenities(hotelWithAmenitiesDTO);
            response.setData(hotel);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_HOTELS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hotels/search")
    public ResponseEntity<?> searchRooms(
            @RequestParam String roomType,
            @RequestParam BigDecimal priceMin,
            @RequestParam BigDecimal priceMax)
    {
        ApiResponse<List<Hotel>> response = new ApiResponse<>();
        try {
            List<Hotel> hotels = hotelService.searchRoomsByTypeAndPriceAndIsBooked(roomType, priceMin, priceMax);
            response.setData(hotels);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
