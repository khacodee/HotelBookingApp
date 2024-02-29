package com.khacv.hotelbookingapp.controller.roomImage;

import com.khacv.hotelbookingapp.dto.room.RoomImageDTO;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.entity.room.RoomImage;
import com.khacv.hotelbookingapp.entity.user.UserInfo;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.room.RoomImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
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
        ApiResponse<List<RoomImage>> response = new ApiResponse<>();
        try {
            List<RoomImage> roomImages = roomImagesService.getListRoomImage();
            response.setData(roomImages);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM_IMAGE");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getRoomImageById(@PathVariable int id){
        ApiResponse<RoomImage> response = new ApiResponse<>();
        try {
            RoomImage roomImage = roomImagesService.getRoomImageById(id);
            response.setData(roomImage);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM_IMAGE");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/images")
    public ResponseEntity<?> createRoomImage(@RequestBody RoomImageDTO roomImageDTO){
        ApiResponse<RoomImage> response = new ApiResponse<>();
        try {
            RoomImage roomImage = roomImagesService.createRoomImage(roomImageDTO);
            response.setData(roomImage);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM_IMAGE");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/images/{id}")
    public ResponseEntity<?> updateRoomImage(@PathVariable int id, @RequestBody RoomImageDTO roomImageDTO){
        ApiResponse<RoomImage> response = new ApiResponse<>();
        try {
            RoomImage roomImage = roomImagesService.updateRoomImage(id, roomImageDTO);
            response.setData(roomImage);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM_IMAGE");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteRoomImage(@PathVariable int id){
        ApiResponse<RoomImage> response = new ApiResponse<>();
        try {
            RoomImage roomImage = roomImagesService.deleteRoomImage(id);
            response.setData(roomImage);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM_IMAGE");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
