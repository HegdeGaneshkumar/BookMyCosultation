package com.upgrad.ratingservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
public class RatingEntity {
    @Id
    String id;
    String doctorId;
    int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
