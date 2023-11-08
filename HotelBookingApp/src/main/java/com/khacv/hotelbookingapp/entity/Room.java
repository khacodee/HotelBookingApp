package com.khacv.hotelbookingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;
    @Column(name = "room_number")
    private int roomNumber;
    @Column(name = "room_type")
    private String roomType;
    private BigDecimal price;
    @Column(name = "is_booked")
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;



}
