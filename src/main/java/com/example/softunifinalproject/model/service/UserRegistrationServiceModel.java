package com.example.softunifinalproject.model.service;


import com.example.softunifinalproject.model.entity.UserRoleEntity;

import java.util.Set;

public class UserRegistrationServiceModel {

    private String username;
    private String password;
    private String picture;
    private Double rating;
    private Set<UserRoleEntity> roles;

    public String getUsername() {
        return username;
    }

    public UserRegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserRegistrationServiceModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public UserRegistrationServiceModel setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public UserRegistrationServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
