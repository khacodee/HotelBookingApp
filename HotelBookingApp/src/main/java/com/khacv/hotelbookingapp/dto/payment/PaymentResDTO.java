package com.khacv.hotelbookingapp.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResDTO {
    private String status;
    private String message;
    private String URL;
}
