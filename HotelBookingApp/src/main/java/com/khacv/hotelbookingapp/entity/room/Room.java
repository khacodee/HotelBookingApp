package com.khacv.hotelbookingapp.entity.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =ROOM)
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ROOM_ID)
    private int id;
    @Column(name = ROOM_NUMBER)
    private int roomNumber;
    @Column(name = ROOM_TYPE)
    private String roomType;
    private BigDecimal price;
    @Column(name = IS_BOOKED)
    private boolean isBooked;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = HOTEL_ID)
    private Hotel hotel;

    @OneToMany(mappedBy = ROOM, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RoomImage> roomImages;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Booking> bookings;


}
