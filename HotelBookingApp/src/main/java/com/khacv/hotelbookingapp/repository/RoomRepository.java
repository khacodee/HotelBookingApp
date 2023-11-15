package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Hotel;
import com.khacv.hotelbookingapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByHotelId(int hotelId);
    Room findById(int id);
}
