package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findById(int id);
}
