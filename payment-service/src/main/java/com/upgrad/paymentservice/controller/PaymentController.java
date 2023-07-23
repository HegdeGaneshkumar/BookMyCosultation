package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.dao.AppointmentDao;
import com.upgrad.paymentservice.entities.AppointmentEntity;
import com.upgrad.paymentservice.entities.ErrorModel;
import com.upgrad.paymentservice.entities.PaymentEntity;
import com.upgrad.paymentservice.exception.RequestedResouceUnavailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class PaymentController {

    @Autowired
    AppointmentDao appointmentDao;

    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentEntity> makePaymentForAppointment(@RequestParam(required = true) String appointmentId){

        try {
            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setAppointmentId(appointmentId);
            //setting some random payment id as its just a placeholder
            paymentEntity.setId(getAlphaNumericString(15));
            paymentEntity.setCreatedDate(LocalDate.now());

            AppointmentEntity appointmentEntity = appointmentDao.findById(appointmentId).get();
            appointmentEntity.setStatus("Confirmed");
            appointmentDao.save(appointmentEntity);
            return new ResponseEntity<>(paymentEntity, HttpStatus.OK);
        }
        catch (RuntimeException e){
            throw new RequestedResouceUnavailable();
        }
    }

    private static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @ExceptionHandler(RequestedResouceUnavailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorModel handleRequestedResourceNotFoundException(){
        return ErrorModel.builder().errorCode("ERR_RESOURCE_NOT_FOUND").errorMessage("appointmentId not found").build();
    }

}
