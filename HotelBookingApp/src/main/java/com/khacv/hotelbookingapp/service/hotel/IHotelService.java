package com.khacv.hotelbookingapp.service.hotel;

import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.dto.hotel.HotelWithAmenitiesDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;

import java.util.List;

public interface IHotelService {

    List<Hotel> getListHotel();
    Hotel getHotelById(int id);

    String createHotel(HotelDTO hotelDTO);

    String createHotelWithAmenities(HotelWithAmenitiesDTO hotelWithAmenitiesDTO);
    String updateHotel(int id, HotelDTO updateHotel);

    String deleteHotel(int id);

    String ActiveHotel(int id);
}
