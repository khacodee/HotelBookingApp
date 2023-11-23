package com.khacv.hotelbookingapp.controller.roomImage;

import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.room.RoomImagesService;
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
public class RoomImageController {

    @Autowired
    private final RoomImagesService roomImagesService;

    public RoomImageController(RoomImagesService roomImagesService) {
        this.roomImagesService = roomImagesService;
    }

    @GetMapping("/images")
    public ResponseEntity<?> getListRoomImage(){
        try {
        return ResponseEntity.ok(roomImagesService.getListRoomImage());
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getRoomImageById(@PathVariable int id){
        try {
        return ResponseEntity.ok(roomImagesService.getRoomImageById(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
