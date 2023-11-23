package com.khacv.hotelbookingapp.service.hotel;


import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private HotelRepository hotelRepository;



    @Override
    public List<Hotel> getListHotel(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<Hotel> result = new ArrayList<>();

        for (Hotel hotel:
             hotels) {
            if(hotel.isActive()){
                result.add(hotel);
            }

        }
        return result;
    }

    @Override
    public Hotel getHotelById(int id){
        return hotelRepository.findById(id);
    }

    @Override
    public String createHotel(HotelDTO hotelDTO) {
        Hotel newHotel = new Hotel();
        newHotel.setName(hotelDTO.getName());
        newHotel.setLocation(hotelDTO.getLocation());
        newHotel.setDescription(hotelDTO.getDescription());
        newHotel.setImage(hotelDTO.getImage());
        newHotel.setPrice(hotelDTO.getPrice());
        newHotel.setActive(hotelDTO.getIsActive());

        hotelRepository.save(newHotel);

        return ADDED_SUCCESSFULLY;
    }


}
