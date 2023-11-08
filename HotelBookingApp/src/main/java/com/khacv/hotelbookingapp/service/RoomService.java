package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Room;
import com.khacv.hotelbookingapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> GetListRoom(){
        return roomRepository.findAll();
    }

    public Room getRoomById(int id){
        return roomRepository.findById(id);
    }
}
