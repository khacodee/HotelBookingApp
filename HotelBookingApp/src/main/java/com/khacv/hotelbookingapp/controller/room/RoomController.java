package com.khacv.hotelbookingapp.controller.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class RoomController {

    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(
            @RequestParam(name = "available", required = false, defaultValue = "false") boolean onlyAvailable) {
        ApiResponse<List<Room>> response = new ApiResponse<>();
        try {
            List<Room> rooms = (onlyAvailable == true)
                    ? roomService.getAvailableRooms()
                    : roomService.getListRoom();
            response.setData(rooms);

        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_ROOMS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable int id){
        ApiResponse<Room> response = new ApiResponse<>();
        try {
            Room room = roomService.getRoomById(id);
            response.setData(room);

        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_ROOMS");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO roomDTO){
       ApiResponse<Room> response = new ApiResponse<>();
       try {
           Room room = roomService.createRoom(roomDTO);
           response.setData(room);
       }catch (Exception e){
           ErrorResponse errorResponse = new ErrorResponse();
           errorResponse.setCode(400);
           errorResponse.setMessage("ERROR_ROOMS");
           List<String> errorMessages = new ArrayList<>();
           errorMessages.add(e.getMessage());
           errorResponse.setErrors(errorMessages);
           response.setError(errorResponse);
           return ResponseEntity.badRequest().body(response);
       }
           return ResponseEntity.ok(response);
    }


    @PutMapping("/rooms/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable int id ,@RequestBody RoomDTO roomUpdate){

        ApiResponse<Room> response = new ApiResponse<>();

        try {
            Room room = roomService.updateRoom(id, roomUpdate);
            response.setData(room);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);

        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable int id){
        ApiResponse<Room> response = new ApiResponse<>();
        try {
            Room room = roomService.deleteRoom(id);
            response.setData(room);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/rooms/search")
    public ResponseEntity<?> searchRooms(
            @RequestParam String roomType,
            @RequestParam BigDecimal priceMin,
            @RequestParam BigDecimal priceMax)
    {
        ApiResponse<List<Room>> response = new ApiResponse<>();
        try {
            List<Room> rooms = roomService.searchRoomsByTypeAndPriceAndIsBooked(roomType, priceMin, priceMax);
            response.setData(rooms);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_ROOM");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
