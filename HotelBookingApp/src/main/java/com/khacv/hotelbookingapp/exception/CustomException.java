package com.khacv.hotelbookingapp.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
@RestControllerAdvice
public class CustomException {
    @ExceptionHandler(value = {UserNotFoundException.class})
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(UserNotFoundException ex, WebRequest req){
        return ResponseEntity.ok(new ErrorResponese(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    //@ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponese> HandlerForbiddenException(ForbiddenException ex, WebRequest req){
        return ResponseEntity.ok(new ErrorResponese(HttpStatus.FORBIDDEN, ex.getMessage()));
    }
    @ExceptionHandler(value = {UnauthorizedException.class})
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponese> HandlerUnauthorizedException(UnauthorizedException ex, WebRequest req){
        return ResponseEntity.ok(new ErrorResponese(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(NotFoundException ex, WebRequest req){
        return ResponseEntity.ok(new ErrorResponese(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    //@ResponseStatus(HttpStatus.NOT_FO)
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(IllegalArgumentException ex, WebRequest req){
        return ResponseEntity.ok(new ErrorResponese(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
