package com.khacv.hotelbookingapp.controller.roomImage;

import com.khacv.hotelbookingapp.service.room.RoomImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class RoomImageController {

    @Autowired
    RoomImagesService roomImagesService;
    @GetMapping("/images")
    public ResponseEntity<?> getListRoomImage(){
        return ResponseEntity.ok(roomImagesService.getListRoomImage());
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getRoomImageById(@PathVariable int id){
        return ResponseEntity.ok(roomImagesService.getRoomImageById(id));
    }
}
