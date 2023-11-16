package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.dto.RoomDTO;
import com.khacv.hotelbookingapp.entity.Room;
import com.khacv.hotelbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<?> getListRoom(){
        return ResponseEntity.ok(roomService.getListRoom());
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
