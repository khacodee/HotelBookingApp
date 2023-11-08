package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Hotel;
import com.khacv.hotelbookingapp.exception.UserNotFoundException;
import com.khacv.hotelbookingapp.repository.HotelRepository;
import com.khacv.hotelbookingapp.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getListHotel(){

        return hotelRepository.findAll();
    }

    public Hotel getHotelById(int id){
        return hotelRepository.findById(id);
    }
}
