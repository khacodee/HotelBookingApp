package com.khacv.hotelbookingapp.dto.guest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDTO {
    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;
}
