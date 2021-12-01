package com.example.softunifinalproject.service;

import com.example.softunifinalproject.model.binding.UserRegisterBindingModel;
import com.example.softunifinalproject.model.entity.UserEntity;

public interface UserService {

    void initializeUser();
    void initializeUserRoles();

    void registerUser(UserRegisterBindingModel userModel);

    UserEntity findByUsername(String name);

    boolean isUsernameFree(String username);

}
