package com.upgrad.userservice.service;

import com.upgrad.userservice.dao.UserRepository;
import com.upgrad.userservice.entities.UserInfoEntity;
import com.upgrad.userservice.exception.RequestedResourceUnavailable;
import com.upgrad.userservice.producer.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private static final String TOPIC_NAME = "user-service";

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer ;

    @Override
    public UserInfoEntity saveUserDetails(UserInfoEntity userInfoEntity) {
        userInfoEntity.setCreatedDate(LocalDate.now());
        UserInfoEntity savedUser = userRepository.save(userInfoEntity);
        try {
            kafkaMessageProducer.publish(TOPIC_NAME, savedUser.getEmailId(), savedUser.toString());
        }
        catch (IOException exception){
            System.out.println(exception);
        }
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
