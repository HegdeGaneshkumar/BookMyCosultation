package com.upgrad.userservice.service;

import com.upgrad.userservice.dao.UserRepository;
import com.upgrad.userservice.entities.UserInfoEntity;
import com.upgrad.userservice.exception.RequestedResourceUnavailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserInfoEntity saveUserDetails(UserInfoEntity userInfoEntity) {
        userInfoEntity.setCreatedDate(LocalDate.now());
        UserInfoEntity savedUser = userRepository.save(userInfoEntity);

        return savedUser;
    }

    @Override
    public UserInfoEntity getUserBasedOnId(String id) {
        try{
            UserInfoEntity userInfoEntity = userRepository.findById(id).get();
            return userInfoEntity;
        }
        catch (RuntimeException e){
            throw new RequestedResourceUnavailable();
        }
    }
}
