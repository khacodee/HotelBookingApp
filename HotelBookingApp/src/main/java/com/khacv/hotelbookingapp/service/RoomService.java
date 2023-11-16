package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.dto.RoomDTO;
import com.khacv.hotelbookingapp.entity.Hotel;
import com.khacv.hotelbookingapp.entity.Room;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.exception.UserNotFoundException;
import com.khacv.hotelbookingapp.repository.HotelRepository;
import com.khacv.hotelbookingapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public List<Room> getListRoom(){
        return roomRepository.findAll();
    }

    public Room getRoomById(int id){
        return roomRepository.findById(id);
    }

    public String createRoom(RoomDTO roomDTO){
        Room room = new Room();

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId());
        if(hotel == null){
            throw new NotFoundException("Not Fount!!");
        }
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setPrice(roomDTO.getPrice());
        room.setBooked(roomDTO.isBooked());
        room.setHotel(hotel);

        roomRepository.save(room);
        return "Create successfully";
    }

    public String updateRoom(int id, RoomDTO updateRoom){
        Room room = roomRepository.findById(id);
        if(room == null){
            throw new NotFoundException("Room not found!");
        }
        Hotel hotel = hotelRepository.findById(updateRoom.getHotelId());
        if(hotel == null){
            throw new NotFoundException("hotel not found!");
        }
        room.setRoomNumber(updateRoom.getRoomNumber());


        room.setRoomType(updateRoom.getRoomType());


        room.setPrice(updateRoom.getPrice());


        room.setBooked(updateRoom.isBooked());

        room.setHotel(hotel);
        // Cập nhật thông tin phòng
        roomRepository.save(room);

        return "Room updated successfully";

    }

    public String deleteRoom(int id){
        // Tìm phòng cần xóa bằng ID
        Room room = roomRepository.findById(id);
        if(room == null){
            throw new NotFoundException("Room not found!");
        }

        // Xóa phòng từ cơ sở dữ liệu
        roomRepository.delete(room);

        return "Room deleted successfully";
    }

}
