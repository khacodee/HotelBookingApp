package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomDTO;
import com.khacv.hotelbookingapp.entity.room.Room;

import java.util.List;

public interface IRoomService {

    List<Room> getListRoom();

    Room getRoomById(int id);

    String createRoom(RoomDTO roomDTO);

    String updateRoom(int id, RoomDTO updateRoom);

    String deleteRoom(int id);

    List<Room> getAvailableRooms();
}
