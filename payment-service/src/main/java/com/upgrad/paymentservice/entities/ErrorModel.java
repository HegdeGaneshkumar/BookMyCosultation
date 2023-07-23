package com.upgrad.paymentservice.entities;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {
    private String errorCode;
    private String errorMessage;
}
