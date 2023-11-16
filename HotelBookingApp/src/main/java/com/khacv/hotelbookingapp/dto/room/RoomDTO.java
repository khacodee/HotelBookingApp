package com.khacv.hotelbookingapp.dto.room;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private int roomNumber;
    private String roomType;
    private BigDecimal price;
    private boolean isBooked;
    private int hotelId;
}
