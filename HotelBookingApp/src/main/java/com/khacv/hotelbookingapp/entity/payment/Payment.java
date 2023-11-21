package com.khacv.hotelbookingapp.entity.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = PAYMENT)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PAYMENT_ID)
    private int id;
    @Column(name= PAYMENT_DATE)
    private Date paymentDate;
    private BigDecimal amount;
    @Column(name= CARD_NUMBER)
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = BOOKING_ID, nullable = false)
    @JsonBackReference
    private Booking booking;

}
