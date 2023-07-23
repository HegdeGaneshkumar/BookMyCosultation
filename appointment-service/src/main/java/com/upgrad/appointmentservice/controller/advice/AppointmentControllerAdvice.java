package com.upgrad.appointmentservice.controller.advice;

import com.upgrad.appointmentservice.entities.ErrorModel;
import com.upgrad.appointmentservice.exception.PaymentPendingException;
import com.upgrad.appointmentservice.exception.RequestedResourceUnavailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentControllerAdvice {
    @ExceptionHandler(PaymentPendingException.class)
    ResponseEntity<ErrorModel> handlePaymentPendingxception(){
        return new ResponseEntity(
                ErrorModel.
                        builder().
                        errorCode("ERR_PAYMENT_PENDING").
                        errorMessage("Prescription cannot be issued since payment is pending").
                        build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestedResourceUnavailable.class)
    ResponseEntity<ErrorModel> handleRequestedResourceNotFoundException(){
        return new ResponseEntity(
                ErrorModel.
                        builder().
                        errorCode("ERR_RESOURCE_NOT_FOUND").
                        errorMessage("Appointment id not found").
                        build(), HttpStatus.BAD_REQUEST);
    }
}
