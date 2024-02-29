package com.khacv.hotelbookingapp.controller.guest;

import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.exception.ErrorResponese;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.guest.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class GuestController {
    @Autowired
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @GetMapping("/guest")
    public ResponseEntity<ApiResponse<List<Guest>>> getAllGuest(){
        ApiResponse<List<Guest>> response = new ApiResponse<>();
        try {
            List<Guest> guests = guestService.getAllGuest();
            response.setData(guests);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_GUEST");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/guest/{id}")
    public ResponseEntity<ApiResponse<Guest>> getGuestById(@PathVariable int id){
        ApiResponse<Guest> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            Guest guest = guestService.getGuestById(id);
            response.setData(guest);
        }catch (Exception e){
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_GUEST");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/guest/{id}")
    public ResponseEntity<ApiResponse<Guest>> updateProfileGuest(@PathVariable int id, @RequestBody GuestDTO updateGuest){
        ApiResponse<Guest> response = new ApiResponse<>();
        try {
            Guest guest = guestService.updateGuestProfile(id, updateGuest);
            response.setData(guest);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_GUEST");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guest")
    public ResponseEntity<ApiResponse<Guest>> createGuest(@RequestBody GuestDTO guestDTO){
        ApiResponse<Guest> response = new ApiResponse<>();

        try {
            Guest guest = guestService.createGuest(guestDTO);
            response.setData(guest);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_GUEST");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/guest/{id}")
    public ResponseEntity<ApiResponse<Guest>> deleteGuest(@PathVariable int id){
        ApiResponse<Guest> response = new ApiResponse<>();
        try {
            Guest guest = guestService.deleteGuest(id);
            response.setData(guest);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_GUEST");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
