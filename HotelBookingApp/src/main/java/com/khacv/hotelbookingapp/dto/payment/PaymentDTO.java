package com.khacv.hotelbookingapp.dto.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import static com.khacv.hotelbookingapp.util.Constants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private Date paymentDate;
    private BigDecimal amount;
    private String status;
    private int bookingId;
}
