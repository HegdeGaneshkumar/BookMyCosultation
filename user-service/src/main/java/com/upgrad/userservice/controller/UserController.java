package com.upgrad.userservice.controller;

import com.upgrad.userservice.entities.UserInfoEntity;
import com.upgrad.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoEntity> createUser(@Valid @RequestBody UserInfoEntity userInfoEntity){
        UserInfoEntity savedUser = userService.saveUserDetails(userInfoEntity);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoEntity> getUserById(@PathVariable String userId){
        UserInfoEntity userInfoEntity = userService.getUserBasedOnId(userId);
        return new ResponseEntity(userInfoEntity, HttpStatus.OK);
    }

    @PostMapping(value = "users/{id}/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadDocuments(@PathVariable String id, @RequestParam(value = "file", required = true) MultipartFile file){

        System.out.println("inside uploadDocuments controller");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "users/{id}/documents/{documentName}")
    public ResponseEntity downloadDocuments(@PathVariable String id, @PathVariable String documentName){

        System.out.println("inside downloadDocuments controller");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/documents/metadata")
    public ResponseEntity getDocumentNames(@PathVariable String id){

        System.out.println("inside getDocumentNames controller");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
