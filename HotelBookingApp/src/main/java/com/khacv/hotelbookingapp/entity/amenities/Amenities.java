package com.khacv.hotelbookingapp.entity.amenities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = AMENITIES)
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;
    @Column(name = AMENITIES_NAME)
    private String name;
}
