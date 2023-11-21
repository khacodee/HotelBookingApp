package com.khacv.hotelbookingapp.entity.guest;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = GUEST)
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = GUEST_ID)
    private int id;
    @Column(name = FULL_NAME)
    private String fullName;
    private String address;
    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

    @OneToMany(mappedBy = GUEST, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Booking> bookings;

    @OneToMany(mappedBy = GUEST,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reviews> reviews;

}
