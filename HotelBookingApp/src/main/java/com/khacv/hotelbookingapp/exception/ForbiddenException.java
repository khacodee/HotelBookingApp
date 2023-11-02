package com.khacv.hotelbookingapp.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
