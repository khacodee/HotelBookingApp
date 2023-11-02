package com.khacv.hotelbookingapp.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
