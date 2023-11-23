package com.khacv.hotelbookingapp.controller.hotel;


import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class HotelController {
    @Autowired
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> getListUser(){
        try {
        return ResponseEntity.ok(hotelService.getListHotel());
    }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
    }
    }



    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> getHotelWithRooms(@PathVariable int id) {
        try {
        return ResponseEntity.ok(hotelService.getHotelById(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }



    @PostMapping("/hotels")
    public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO){
        try {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
    }catch (Exception e){
        throw new IllegalArgumentException(e.getMessage());
    }
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<?> updateHotel( @PathVariable int id,@RequestBody HotelDTO hotelDTO){
        try {
            return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id){
        try {
            return ResponseEntity.ok(hotelService.deleteHotel(id));
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/active-hotel/{id}")
    public ResponseEntity<?> ActiveHotel(@PathVariable int id){
        try {
            return ResponseEntity.ok(hotelService.ActiveHotel(id));
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
