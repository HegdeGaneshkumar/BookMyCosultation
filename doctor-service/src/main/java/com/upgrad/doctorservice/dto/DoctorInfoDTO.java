package com.upgrad.doctorservice.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class DoctorInfoDTO {
    @NotBlank(message = "firstName cannot be blank")
    @Size(max = 20, message = "firstName cannot be more than 20 chars")
    String firstName;
    @NotBlank(message = "lastName cannot be blank")
    @Size(max = 20, message = "lastName cannot be more than 20 chars")
    String lastName;
    @NotNull(message = "dob cannot be blank")
    LocalDate dob;
    @Size(max = 10)
    @NotBlank(message = "mobile cannot be blank")
    @Pattern(regexp = "^[0-9]{10}", message = "mobile number not valid")
    String mobile;
    @NotBlank(message = "emailId cannot be blank")
    @Email(message = "not a valid email id")
    String emailId;
    @NotBlank(message = "pan cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]{10}", message = "pan pattern is not accepted")
    String pan;

    String speciality;

    String status;

    LocalDate registrationDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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
