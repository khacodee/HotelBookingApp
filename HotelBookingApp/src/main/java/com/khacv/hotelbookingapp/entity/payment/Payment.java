package com.khacv.hotelbookingapp.entity.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;
    @Column(name="payment_date")
    private Date paymentDate;
    private BigDecimal amount;
    @Column(name="card_number")
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference
    private Booking booking;

}
