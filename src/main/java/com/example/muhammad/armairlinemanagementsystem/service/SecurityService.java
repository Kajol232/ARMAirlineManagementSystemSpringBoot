package com.example.muhammad.armairlinemanagementsystem.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
