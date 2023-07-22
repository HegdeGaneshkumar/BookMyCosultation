package com.upgrad.userservice.dao;

import com.upgrad.userservice.entities.UserInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserInfoEntity, String> {
}
