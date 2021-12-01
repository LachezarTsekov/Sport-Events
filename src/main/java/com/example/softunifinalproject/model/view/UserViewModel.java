package com.example.softunifinalproject.model.view;

public class UserViewModel {
    private String username;
    private String password;
    private String picture;
    private Double rating;

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserViewModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public UserViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public UserViewModel setRating(Double rating) {
        this.rating = rating;
        return this;
    }
}
