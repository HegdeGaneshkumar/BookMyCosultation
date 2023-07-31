package com.upgrad.ratingservice.controller;

import com.upgrad.ratingservice.dao.RatingRepository;
import com.upgrad.ratingservice.entities.RatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    //@Todo: A message is sent to a Kafka topic to which the doctor service is listening.
    // @TODO: The doctor service then updates the average rating of the doctor.
    @PostMapping(value = "/ratings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveRating(@RequestBody RatingEntity ratingEntity){
        ratingRepository.save(ratingEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
