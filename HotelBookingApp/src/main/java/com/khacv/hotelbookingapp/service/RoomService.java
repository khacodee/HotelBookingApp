package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Hotel;
import com.khacv.hotelbookingapp.entity.Room;
import com.khacv.hotelbookingapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getListRoom(){
        return roomRepository.findAll();
    }

    public Room getRoomById(int id){
        return roomRepository.findById(id);
    }

}
