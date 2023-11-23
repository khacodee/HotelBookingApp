package com.khacv.hotelbookingapp.controller.guest;

import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.exception.ErrorResponese;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.guest.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class GuestController {
    @Autowired
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @GetMapping("/guest")
    public ResponseEntity<?> getAllGuest(){
        try {

        return ResponseEntity.ok(guestService.getAllGuest());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }
    @GetMapping("/guest/{id}")
    public ResponseEntity<?> getGuestById(@PathVariable int id){
//        try {
        return ResponseEntity.ok(guestService.getGuestById(id));
//        }catch (Exception e){
//            ErrorResponese errorResponse = new ErrorResponese(HttpStatus.NOT_FOUND,  e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//        }
    }

    @PutMapping("/guest/{id}")
    public ResponseEntity<?> updateProfileGuest(@PathVariable int id, @RequestBody GuestDTO guest){
        try {
        return ResponseEntity.ok(guestService.updateGuestProfile(id, guest));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PostMapping("/guest")
    public ResponseEntity<?> createGuest(@RequestBody GuestDTO guestDTO){
        try {
        return ResponseEntity.ok(guestService.createGuest(guestDTO));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/guest/{id}")
    public ResponseEntity<?> deleteGuest(@PathVariable int id){
        try {
        return ResponseEntity.ok(guestService.deleteGuest(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
