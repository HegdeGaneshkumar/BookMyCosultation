package com.upgrad.JWTGenerator.security;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}