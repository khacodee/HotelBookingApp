package com.khacv.hotelbookingapp.service.hotel;

import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.dto.hotel.HotelWithAmenitiesDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.room.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IHotelService {

    List<Hotel> getListHotelIsActive();

    Hotel getHotelByIdWithRoom(int id);

    List<Hotel> getListHotel();
    Hotel getHotelById(int id);

    Hotel createHotel(HotelDTO hotelDTO);

    Hotel createHotelWithAmenities(HotelWithAmenitiesDTO hotelWithAmenitiesDTO);
    Hotel updateHotel(int id, HotelDTO updateHotel);

    Hotel deleteHotel(int id);

    Hotel ActiveHotel(int id);

    List<Hotel> searchRoomsByTypeAndPriceAndIsBooked(String roomType, BigDecimal priceMin, BigDecimal priceMax);
}
