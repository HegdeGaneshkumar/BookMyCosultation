package com.upgrad.doctorservice.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class DoctorInfoDTO {
    @NotBlank
    @Size(max = 20)
    String firstName;
    @NotBlank
    @Size(max = 20)
    String lastName;
    @NotBlank
    LocalDate dob;
    @Size(max = 10)
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}")
    String mobile;
    @NotBlank
    @Email
    String emailId;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]{10}")
    String pan;

    String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
