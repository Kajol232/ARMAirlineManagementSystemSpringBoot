package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.RegisterUserModel;
import com.example.muhammad.armairlinemanagementsystem.model.User;
import org.springframework.security.core.Authentication;

import java.text.ParseException;

public interface IUserService {
    User addUser(RegisterUserModel registerUserModel, String roleName) throws ParseException;
    String getSignedUser(Authentication authentication);
    User updateUserDetails(RegisterUserModel registerUserModel, long id);
}
