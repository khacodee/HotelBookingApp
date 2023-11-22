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
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(UserNotFoundException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<ErrorResponese> HandlerForbiddenException(ForbiddenException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<ErrorResponese> HandlerUnauthorizedException(UnauthorizedException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(NotFoundException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponese> HandlerNotFoundException(IllegalArgumentException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<ErrorResponese> handleAccessDeniedException(AccessDeniedException ex, WebRequest req) {
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    //@ResponseStatus(HttpStatus.NOT_FO)
    public ResponseEntity<ErrorResponese> HandlerTokenExpiredException(IllegalArgumentException ex, WebRequest req){
        ErrorResponese errorResponse = new ErrorResponese(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
