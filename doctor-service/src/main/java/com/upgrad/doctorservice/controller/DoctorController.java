package com.upgrad.doctorservice.controller;

import com.upgrad.doctorservice.dto.DoctorInfoDTO;
import com.upgrad.doctorservice.entities.DoctorInfoEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    //Todo -  Role: USER, ADMIN. If the token has the role of either USER or ADMIN, then the endpoint can be accessed.
    //Validation
    @PostMapping(value = "/doctor", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorInfoEntity> registerDoctor( @RequestBody DoctorInfoDTO doctorInfoDTO){

        //call service layer
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/doctor")
    public ResponseEntity<DoctorInfoEntity> getDoctors( ){

        //call service layer
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
