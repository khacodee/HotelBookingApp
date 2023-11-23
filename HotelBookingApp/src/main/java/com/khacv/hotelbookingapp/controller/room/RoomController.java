package com.khacv.hotelbookingapp.controller.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class RoomController {

    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(@RequestParam(name = "available", required = false, defaultValue = "false") boolean onlyAvailable) {
        try {
        if (onlyAvailable) {

            return ResponseEntity.ok(roomService.getAvailableRooms());
        } else {

            return ResponseEntity.ok(roomService.getListRoom());
        }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable int id){
        try {
        return ResponseEntity.ok(roomService.getRoomById(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO roomDTO){
        try {
        return ResponseEntity.ok(roomService.createRoom(roomDTO));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    @PutMapping("/rooms/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable int id ,@RequestBody RoomDTO roomUpdate){
        try {
        return ResponseEntity.ok(roomService.updateRoom(id, roomUpdate));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable int id){
        try {
        return ResponseEntity.ok(roomService.deleteRoom(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
