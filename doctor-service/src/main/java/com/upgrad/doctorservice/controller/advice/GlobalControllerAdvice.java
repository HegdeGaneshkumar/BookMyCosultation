package com.upgrad.doctorservice.controller.advice;

import com.upgrad.doctorservice.entities.ErrorModel;
import com.upgrad.doctorservice.exception.RequestedResourceUnavailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorModel> handleMethodArgNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity(
                ErrorModel.
                        builder().
                        errorCode("ERR_INVALID_INPUT").
                        errorMessage("Invalid input. Parameter name: ").
                        errorFieldList(e.getBindingResult().getFieldErrors().stream().map(fe-> fe.getDefaultMessage()).collect(Collectors.toList())).
                        build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestedResourceUnavailable.class)
    ResponseEntity<ErrorModel> handleRequestedResourceNotFoundException(){
        return new ResponseEntity(
                ErrorModel.
                        builder().
                        errorCode("ERR_RESOURCE_NOT_FOUND").
                        errorMessage("Requested Resource is not available").
                        build(), HttpStatus.BAD_REQUEST);
    }
}
