package com.khacv.hotelbookingapp.repository.room;

import com.khacv.hotelbookingapp.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByHotelId(int hotelId);
    Room findById(int id);

}
