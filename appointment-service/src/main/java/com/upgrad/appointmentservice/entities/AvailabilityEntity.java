package com.upgrad.appointmentservice.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "availability")
public class AvailabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    LocalDate availabilityDate;
    String doctorId;
    boolean isBooked;
    String timeslot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(LocalDate availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        return "AvailabilityEntity{" +
                "id=" + id +
                ", availabilityDate=" + availabilityDate +
                ", doctorId='" + doctorId + '\'' +
                ", isBooked=" + isBooked +
                ", timeslot='" + timeslot + '\'' +
                '}';
    }
}
