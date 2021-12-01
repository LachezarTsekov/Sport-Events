package com.example.softunifinalproject.model.view;

import com.example.softunifinalproject.model.entity.enums.SportCategoryEnum;

import java.time.LocalDateTime;

public class EventViewModel {

    private Long id;
    private String name;
    private String description;
    private String picture;
    private String startDate;
    private String endDate;
    private String location;
    private String category;

    public String getStartDate() {
        return startDate;
    }

    public EventViewModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate.toString().replace("T", " ");
        return this;
    }

    public String getName() {
        return name;
    }

    public EventViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public EventViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }



    public String getEndDate() {
        return endDate;
    }

    public EventViewModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate.toString().replace("T", " ") ;
        return this;
    }

    public Long getId() {
        return id;
    }

    public EventViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public EventViewModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public EventViewModel setCategory(SportCategoryEnum category) {
        this.category = category.toString();
        return this;
    }
}
