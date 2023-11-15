package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.dto.GuestDTO;
import com.khacv.hotelbookingapp.entity.Guest;
import com.khacv.hotelbookingapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/guest/{id}")
    public ResponseEntity<?> updateProfileGuest(@PathVariable int id, @RequestBody GuestDTO guest){
        return ResponseEntity.ok(guestService.updateGuestProfile(id, guest));
    }
}
