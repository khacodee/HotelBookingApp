package com.khacv.hotelbookingapp.controller.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(@RequestParam(name = "available", required = false, defaultValue = "false") boolean onlyAvailable) {
        if (onlyAvailable) {

            return ResponseEntity.ok(roomService.getAvailableRooms());
        } else {

            return ResponseEntity.ok(roomService.getListRoom());
        }
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable int id){
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok(roomService.createRoom(roomDTO));
    }


    @PutMapping("/rooms/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable int id ,@RequestBody RoomDTO roomUpdate){
        return ResponseEntity.ok(roomService.updateRoom(id, roomUpdate));
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable int id){
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }
}
