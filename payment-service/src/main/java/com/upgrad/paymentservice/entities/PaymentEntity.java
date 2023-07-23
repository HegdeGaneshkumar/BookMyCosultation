package com.upgrad.paymentservice.entities;

import java.time.LocalDate;

public class PaymentEntity {
    String id;
    String appointmentId;
    LocalDate createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id='" + id + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
