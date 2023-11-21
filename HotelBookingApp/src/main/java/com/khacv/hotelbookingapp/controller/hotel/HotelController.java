package com.khacv.hotelbookingapp.controller.hotel;


import com.khacv.hotelbookingapp.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
    }
    }



    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> getHotelWithRooms(@PathVariable int id) {
        try {
        return ResponseEntity.ok(hotelService.getHotelById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }
}
