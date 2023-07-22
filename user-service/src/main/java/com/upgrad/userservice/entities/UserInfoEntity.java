package com.upgrad.userservice.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "user")
public class UserInfoEntity {
    @Id
    String id;
    @NotBlank(message = "firstName cannot be blank")
    String firstName;
    @NotBlank(message = "lastName cannot be blank")
    String lastName;
    @NotNull(message = "dob cannot be blank")
    LocalDate dob;
    @NotBlank(message = "mobile cannot be blank")
    @Pattern(regexp = "^[0-9]{10}", message = "mobile number not valid")
    String mobile;
    @NotBlank(message = "emailId cannot be blank")
    @Email(message = "not a valid email id")
    String emailId;
    LocalDate createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", mobile='" + mobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
