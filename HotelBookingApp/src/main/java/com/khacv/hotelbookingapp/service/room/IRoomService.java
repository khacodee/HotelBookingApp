package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.entity.room.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IRoomService {

    List<Room> getListRoom();

    Room getRoomById(int id);

    Room createRoom(RoomDTO roomDTO);

    Room updateRoom(int id, RoomDTO updateRoom);

    Room deleteRoom(int id);

    List<Room> getAvailableRooms();

    List<Room> searchRoomsByTypeAndPriceAndIsBooked(String roomType, BigDecimal priceMin, BigDecimal priceMax);
}
