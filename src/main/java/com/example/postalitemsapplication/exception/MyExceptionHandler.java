package com.example.postalitemsapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseJSON> myExceptionHandler(NotFoundException exception){
        ExceptionResponseJSON responseJSON = new ExceptionResponseJSON();
        responseJSON.setMessage(exception.getMessage());

        return new ResponseEntity<>(responseJSON, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseJSON> globalExceptionHandler(Exception exception){
        ExceptionResponseJSON responseJSON = new ExceptionResponseJSON();
        responseJSON.setMessage(exception.getMessage());

        return new ResponseEntity<>(responseJSON, HttpStatus.BAD_REQUEST);
    }
}
