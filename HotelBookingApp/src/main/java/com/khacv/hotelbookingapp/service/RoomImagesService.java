package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.RoomImage;
import com.khacv.hotelbookingapp.repository.RoomImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomImagesService {

    @Autowired
    RoomImagesRepository roomImagesRepository;

    public List<RoomImage> getListRoomImage(){
        return roomImagesRepository.findAll();
    }

    public RoomImage getRoomImageById(int id){
        return roomImagesRepository.findById(id);
    }
}
