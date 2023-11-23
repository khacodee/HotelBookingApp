package com.khacv.hotelbookingapp.dto.hotel;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import com.khacv.hotelbookingapp.entity.room.Room;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static com.khacv.hotelbookingapp.util.Constants.IMAGE_URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private String name;
    private String location;
    private String description;
    private String image;
    private BigDecimal price;
    private Boolean isActive;


}
