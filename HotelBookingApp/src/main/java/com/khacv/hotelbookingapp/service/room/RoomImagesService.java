package com.khacv.hotelbookingapp.service.room;

import com.khacv.hotelbookingapp.entity.room.RoomImage;
import com.khacv.hotelbookingapp.repository.room.RoomImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomImagesService implements IRoomImagesService {

    @Autowired
    RoomImagesRepository roomImagesRepository;

    @Override
    public List<RoomImage> getListRoomImage(){
        return roomImagesRepository.findAll();
    }

    @Override
    public RoomImage getRoomImageById(int id){
        return roomImagesRepository.findById(id);
    }
}
