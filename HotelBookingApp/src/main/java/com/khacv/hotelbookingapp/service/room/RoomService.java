package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import com.khacv.hotelbookingapp.repository.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class RoomService implements IRoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Room> getListRoom(){
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(int id){
        return roomRepository.findById(id);
    }

    @Override
    public String createRoom(RoomDTO roomDTO){
        Room room = new Room();

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId());
        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setPrice(roomDTO.getPrice());
        room.setBooked(roomDTO.isBooked());
        room.setHotel(hotel);

        roomRepository.save(room);
        return ADDED_SUCCESSFULLY;
    }

    @Override
    public String updateRoom(int id, RoomDTO updateRoom){
        Room room = roomRepository.findById(id);
        if(room == null){
            throw new NotFoundException(NOT_FOUND);
        }
        Hotel hotel = hotelRepository.findById(updateRoom.getHotelId());
        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        room.setRoomNumber(updateRoom.getRoomNumber());


        room.setRoomType(updateRoom.getRoomType());


        room.setPrice(updateRoom.getPrice());


        room.setBooked(updateRoom.isBooked());

        room.setHotel(hotel);
        // Cập nhật thông tin phòng
        roomRepository.save(room);

        return UPDATE_SUCCESSFUL;

    }

    @Override
    public String deleteRoom(int id){
        Room room = roomRepository.findById(id);
        if(room == null){
            throw new NotFoundException(NOT_FOUND);
        }

        roomRepository.delete(room);

        return DELETE_SUCCESSFUL;
    }

}
