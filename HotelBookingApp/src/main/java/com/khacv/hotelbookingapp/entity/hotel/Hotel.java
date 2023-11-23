package com.khacv.hotelbookingapp.entity.hotel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = HOTEL)
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;
    private String name;
    private String location;
    private String description;
    @Column(name = IMAGE_URL)
    private String image;
    private BigDecimal price;
    private boolean isActive;



    @OneToMany(mappedBy = HOTEL, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Room> rooms;


    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
            name = HOTEL_AMENITIES,
            joinColumns = @JoinColumn(name = HOTEL_ID),
            inverseJoinColumns = @JoinColumn(name = AMENITIES_ID)
    )
    private  Set<Amenities> amenities = new HashSet<>();


    @OneToMany(mappedBy = HOTEL,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reviews> reviews;



}
