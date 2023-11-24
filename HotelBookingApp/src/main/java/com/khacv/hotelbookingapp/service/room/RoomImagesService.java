package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.dto.room.RoomImageDTO;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.entity.room.RoomImage;
import com.khacv.hotelbookingapp.repository.room.RoomImagesRepository;
import com.khacv.hotelbookingapp.repository.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class RoomImagesService implements IRoomImagesService {

    @Autowired
    private RoomImagesRepository roomImagesRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomImage> getListRoomImage(){
        return roomImagesRepository.findAll();
    }

    @Override
    public RoomImage getRoomImageById(int id){
        return roomImagesRepository.findById(id);
    }

    @Override
    public String createRoomImage(RoomImageDTO roomImageDTO) {
        RoomImage newRoomImage = new RoomImage();
        newRoomImage.setImageUrl(roomImageDTO.getImageUrl());
        Room room = roomRepository.findById(roomImageDTO.getRoomId());
        if (room == null){
            throw new IllegalArgumentException(NOT_FOUND);
        }
        newRoomImage.setRoom(room);
        roomImagesRepository.save(newRoomImage);
        return ADDED_SUCCESSFULLY;
    }

}
