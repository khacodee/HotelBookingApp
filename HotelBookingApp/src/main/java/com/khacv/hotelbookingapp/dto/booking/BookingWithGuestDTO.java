package com.khacv.hotelbookingapp.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingWithGuestDTO {
    private Date checkInDate;
    private Date checkOutDate;
    private String guestFullName;
    private String guestAddress;
    private String guestEmail;
    private String guestPhoneNumber;
    private String paymentMethod;
    private int roomId;
    private BigDecimal totalPrice;
    private String bookingStatus;
}
