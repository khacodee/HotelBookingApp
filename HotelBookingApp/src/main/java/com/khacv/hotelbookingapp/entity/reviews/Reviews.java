package com.khacv.hotelbookingapp.entity.reviews;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = REVIEWS)
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = REVIEW_ID)
    private int id;
    private int rating;
    private String comment;
    @Column(name = REVIEW_DATE)
    private Date reviewDate;
    @ManyToOne
    @JoinColumn(name = HOTEL_ID, nullable = false)
    @JsonBackReference
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = GUEST_ID, nullable = false)
    @JsonBackReference
    private Guest guest;


}
