package com.khacv.hotelbookingapp.dto;

import com.khacv.hotelbookingapp.entity.Hotel;
import com.khacv.hotelbookingapp.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelWithRooms {
    private Hotel hotel;

    private List<Room> rooms;
}
