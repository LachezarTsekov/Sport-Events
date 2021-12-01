package com.example.softunifinalproject.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String picture;
    private Double rating;
    private Set<UserRoleEntity> roles;
    private Set<EventEntity> createdEvents;
    private Set<EventEntity> events;

    public UserEntity() {
        this.roles = new HashSet<>();
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column()
    public String getPicture() {
        return picture;
    }

    public UserEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    @Column
    public Double getRating() {
        return rating;
    }

    public UserEntity setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }


    @OneToMany
    public Set<EventEntity> getCreatedEvents() {
        return createdEvents;
    }

    public UserEntity setCreatedEvents(Set<EventEntity> createdEvents) {
        this.createdEvents = createdEvents;
        return this;
    }

    @ManyToMany
    public Set<EventEntity> getEvents() {
        return events;
    }

    public UserEntity setEvents(Set<EventEntity> events) {
        this.events = events;
        return this;
    }
}
