package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomImageDTO;
import com.khacv.hotelbookingapp.entity.room.RoomImage;

import java.util.List;

public interface IRoomImagesService {
    List<RoomImage> getListRoomImage();

    RoomImage getRoomImageById(int id);

    RoomImage createRoomImage(RoomImageDTO roomImageDTO);

    RoomImage updateRoomImage(int id, RoomImageDTO roomImageDTO);

    RoomImage deleteRoomImage(int id);
}
