package com.khacv.hotelbookingapp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRoomDTO {

    private Date checkInDate;

    private Date checkOutDate;

    private BigDecimal totalPrice;

    private String bookingStatus;

    private int guestId;

    private int roomId;
}
