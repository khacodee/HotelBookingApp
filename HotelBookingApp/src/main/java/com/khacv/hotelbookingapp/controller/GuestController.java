package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class GuestController {
    @Autowired
    private GuestService guestService;


    @GetMapping("/guest")
    public ResponseEntity<?> getAllGuest(){
        return ResponseEntity.ok(guestService.getAllGuest());
    }
    @GetMapping("/guest/{id}")
    public ResponseEntity<?> getGuestById(@PathVariable int id){
        return ResponseEntity.ok(guestService.getGuestById(id));
    }
}
