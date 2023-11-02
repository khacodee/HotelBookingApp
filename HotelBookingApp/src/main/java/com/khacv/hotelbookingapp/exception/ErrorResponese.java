package com.khacv.hotelbookingapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponese {
    private HttpStatus status;
    private String message;
}