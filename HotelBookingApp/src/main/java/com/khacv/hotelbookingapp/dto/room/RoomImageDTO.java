package com.khacv.hotelbookingapp.dto.room;

import com.khacv.hotelbookingapp.entity.room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomImageDTO {
    private int roomId;
    private String imageUrl;
}
