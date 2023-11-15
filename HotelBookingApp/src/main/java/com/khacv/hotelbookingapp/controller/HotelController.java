package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.dto.HotelWithRooms;
import com.khacv.hotelbookingapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping("/hotels")
    public ResponseEntity<?> getListUser(){
        return ResponseEntity.ok(hotelService.getListHotel());
    }



    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> getHotelWithRooms(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}
