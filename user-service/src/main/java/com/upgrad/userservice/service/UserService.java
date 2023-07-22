package com.upgrad.userservice.service;

import com.upgrad.userservice.entities.UserInfoEntity;

public interface UserService {

    public UserInfoEntity saveUserDetails(UserInfoEntity userInfoEntity);

    public UserInfoEntity getUserBasedOnId(String id);
}
