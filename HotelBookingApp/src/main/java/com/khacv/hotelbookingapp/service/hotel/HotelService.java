package com.khacv.hotelbookingapp.service.hotel;


import com.khacv.hotelbookingapp.dto.hotel.HotelDTO;
import com.khacv.hotelbookingapp.dto.hotel.HotelWithAmenitiesDTO;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.amenities.AmenitiesRepository;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenitiesRepository amenitiesRepository;



    @Override
    public List<Hotel> getListHotelIsActive(){
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
    public Hotel getHotelByIdWithRoom(int id) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        List<Room> rooms = hotel.getRooms();
        List<Room> availableRooms = rooms.stream()
                .filter(room -> !room.isBooked())
                .collect(Collectors.toList());
        hotel.setRooms(availableRooms);
        return hotel;
    }

    @Override
    public List<Hotel> getListHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(int id){
        return hotelRepository.findById(id);
    }



    @Override
    public Hotel createHotel(HotelDTO hotelDTO) {
        Hotel newHotel = new Hotel();
        newHotel.setName(hotelDTO.getName());
        newHotel.setLocation(hotelDTO.getLocation());
        newHotel.setDescription(hotelDTO.getDescription());
        newHotel.setImage(hotelDTO.getImage());
        newHotel.setPrice(hotelDTO.getPrice());
        newHotel.setActive(hotelDTO.getIsActive());

        hotelRepository.save(newHotel);

        return newHotel;
    }

    @Override
    public Hotel createHotelWithAmenities(HotelWithAmenitiesDTO hotelWithAmenitiesDTO) {
        Hotel newHotel = new Hotel();
        newHotel.setName(hotelWithAmenitiesDTO.getName());
        newHotel.setLocation(hotelWithAmenitiesDTO.getLocation());
        newHotel.setDescription(hotelWithAmenitiesDTO.getDescription());
        newHotel.setImage(hotelWithAmenitiesDTO.getImage());
        newHotel.setPrice(hotelWithAmenitiesDTO.getPrice());
        newHotel.setActive(hotelWithAmenitiesDTO.getIsActive());

        for (int i = 0; i < hotelWithAmenitiesDTO.getAmenities().size(); i++) {
            Amenities amenities = new Amenities();
            amenities.setId(hotelWithAmenitiesDTO.getAmenities().get(i));
            newHotel.getAmenities().add(amenities);
        }
        hotelRepository.save(newHotel);
        return newHotel;
    }

    @Override
    public Hotel updateHotel(int id, HotelDTO updateHotel) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel != null){
            hotel.setName(updateHotel.getName());
            hotel.setLocation(updateHotel.getLocation());
            hotel.setDescription(updateHotel.getDescription());
            hotel.setImage(updateHotel.getImage());
            hotel.setPrice(updateHotel.getPrice());
            hotel.setActive(updateHotel.getIsActive());
        }
        hotelRepository.save(hotel);

        return hotel;
    }

    @Override
    public Hotel deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id);
       if(hotel != null){
           hotelRepository.delete(hotel);
       }
       return hotel;
    }

    @Override
    public Hotel ActiveHotel(int id) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel != null){
            hotel.setActive(true);
            hotelRepository.save(hotel);
        }

        return hotel;
    }

    @Override
    public List<Hotel> searchRoomsByTypeAndPriceAndIsBooked(String roomType, BigDecimal priceMin, BigDecimal priceMax) {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<Hotel> hotelsWithMatchingRooms = new ArrayList<>();

        for (Hotel hotel:
                hotelList) {
            List<Room> result = new ArrayList<>();
            for (Room room:
                    hotel.getRooms()) {
                if(room.getRoomType().equals(roomType) && room.getPrice().compareTo(priceMin) >= 0 && room.getPrice().compareTo(priceMax) <= 0 && !room.isBooked()){
                    result.add(room);
                }
            }
            if (!result.isEmpty()) {
                Hotel hotelWithMatchingRooms = new Hotel();
                hotelWithMatchingRooms.setId(hotel.getId());
                hotelWithMatchingRooms.setName(hotel.getName());
                hotelWithMatchingRooms.setLocation(hotel.getLocation());
                hotelWithMatchingRooms.setDescription(hotel.getDescription());
                hotelWithMatchingRooms.setImage(hotel.getImage());
                hotelWithMatchingRooms.setPrice(hotel.getPrice());
                hotelWithMatchingRooms.setActive(hotel.isActive());
                hotelWithMatchingRooms.setRooms(result);
                hotelsWithMatchingRooms.add(hotelWithMatchingRooms);
            }
        }


        return hotelsWithMatchingRooms;
    }


}
