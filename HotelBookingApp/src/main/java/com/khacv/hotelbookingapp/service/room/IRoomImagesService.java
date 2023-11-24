package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomImageDTO;
import com.khacv.hotelbookingapp.entity.room.RoomImage;

import java.util.List;

public interface IRoomImagesService {
    List<RoomImage> getListRoomImage();

    RoomImage getRoomImageById(int id);

    String createRoomImage(RoomImageDTO roomImageDTO);
}
