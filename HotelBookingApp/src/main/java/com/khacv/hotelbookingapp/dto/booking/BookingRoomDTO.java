package com.khacv.hotelbookingapp.dto.booking;

import com.khacv.hotelbookingapp.entity.guest.Guest;
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

    private String paymentMethod;

    private String bookingStatus;

    private int guestId;

    private int roomId;
}
