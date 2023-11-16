package com.khacv.hotelbookingapp.service.hotel;


import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private HotelRepository hotelRepository;



    @Override
    public List<Hotel> getListHotel(){

        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(int id){
        return hotelRepository.findById(id);
    }


}
