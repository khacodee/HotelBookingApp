package com.khacv.hotelbookingapp.service.hotel;


import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.exception.NotFoundException;
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

    @Override
    public String updateHotel(int id, HotelDTO updateHotel) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        hotel.setName(updateHotel.getName());
        hotel.setLocation(updateHotel.getLocation());
        hotel.setDescription(updateHotel.getDescription());
        hotel.setImage(updateHotel.getImage());
        hotel.setPrice(updateHotel.getPrice());
        hotel.setActive(updateHotel.getIsActive());
        return UPDATE_SUCCESSFUL;
    }

    @Override
    public String deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id);
       if(hotel == null){
           throw new NotFoundException(NOT_FOUND);
       }
        hotelRepository.delete(hotel);
        return DELETE_SUCCESSFUL;
    }

    @Override
    public String ActiveHotel(int id) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        hotel.setActive(true);
        hotelRepository.save(hotel);
        return UPDATE_SUCCESSFUL;
    }


}
