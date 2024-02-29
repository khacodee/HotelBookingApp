package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import com.khacv.hotelbookingapp.repository.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        Room room = roomRepository.findById(id);
        if(room == null){
            throw new NotFoundException(NOT_FOUND);
        }
        return room;
    }

    @Override
    public Room createRoom(RoomDTO roomDTO){
        Room room = new Room();

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId());
        if(hotel != null){
            room.setHotel(hotel);
        }
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setPrice(roomDTO.getPrice());
        room.setBooked(roomDTO.isBooked());


        roomRepository.save(room);
        return room;
    }

    @Override
    public Room updateRoom(int id, RoomDTO updateRoom){
        Room room = roomRepository.findById(id);
        if(room != null){
            room.setRoomNumber(updateRoom.getRoomNumber());


            room.setRoomType(updateRoom.getRoomType());


            room.setPrice(updateRoom.getPrice());


            room.setBooked(updateRoom.isBooked());
        }
        Hotel hotel = hotelRepository.findById(updateRoom.getHotelId());
        if(hotel != null){
            room.setHotel(hotel);
        }

        // Cập nhật thông tin phòng
        roomRepository.save(room);

        return room;

    }

    @Override
    public Room deleteRoom(int id){
        Room room = roomRepository.findById(id);
        if(room != null){
            roomRepository.delete(room);
        }
        return room;
    }

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> rooms = roomRepository.findAll();

        List<Room> result = new ArrayList<>();

        for (Room room:
             rooms) {
            if(!room.isBooked()){
               result.add(room);
            }
        }

        return result;
    }

    @Override
    public List<Room> searchRoomsByTypeAndPriceAndIsBooked(String roomType, BigDecimal priceMin, BigDecimal priceMax) {
        List<Room> roomList = roomRepository.findAll();
        List<Room> result = new ArrayList<>();

        for (Room room:
             roomList) {
            if(room.getRoomType().contains(roomType) && room.getPrice().compareTo(priceMin) >= 0 && room.getPrice().compareTo(priceMax) <= 0 && room.isBooked() == false){
                room.getHotel();
                result.add(room);
            }
        }
        if(result.isEmpty()){
            throw new NotFoundException(NOT_FOUND);
        }

        return result;
    }


}
