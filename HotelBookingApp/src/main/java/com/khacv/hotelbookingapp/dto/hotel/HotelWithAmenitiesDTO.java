package com.khacv.hotelbookingapp.dto.hotel;

import com.khacv.hotelbookingapp.entity.amenities.Amenities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelWithAmenitiesDTO {
    private String name;
    private String location;
    private String description;
    private String image;
    private BigDecimal price;
    private Boolean isActive;

    private List<Integer> amenities;
}
