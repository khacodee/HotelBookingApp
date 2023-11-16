package com.khacv.hotelbookingapp.repository.room;

import com.khacv.hotelbookingapp.entity.room.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomImagesRepository extends JpaRepository<RoomImage, Integer> {
    RoomImage findById(int id);
}
