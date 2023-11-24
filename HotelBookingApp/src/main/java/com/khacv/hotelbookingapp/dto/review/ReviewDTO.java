package com.khacv.hotelbookingapp.dto.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private int rating;
    private String comment;
    private Date reviewDate;
    private int hotelId;
    private int guestId;

}
