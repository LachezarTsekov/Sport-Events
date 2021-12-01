package com.example.softunifinalproject.model.binding;


import com.example.softunifinalproject.model.entity.PictureEntity;
import com.example.softunifinalproject.model.entity.enums.SportCategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class EventBindingModel {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer maxPeople;
    private Integer attendant;
    private String location;
    private String description;
    private SportCategoryEnum category;
    private MultipartFile pictures;

    @NotBlank
    public String getName() {
        return name;
    }

    public EventBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public EventBindingModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public EventBindingModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    @NotNull
    @Positive
    public Integer getMaxPeople() {
        return maxPeople;
    }

    public EventBindingModel setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
        return this;
    }

    public Integer getAttendant() {
        return attendant;
    }

    public EventBindingModel setAttendant(Integer attendant) {
        this.attendant = attendant;
        return this;
    }

    @NotBlank
    public String getLocation() {
        return location;
    }

    public EventBindingModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public SportCategoryEnum getCategory() {
        return category;
    }

    public EventBindingModel setCategory(SportCategoryEnum category) {
        this.category = category;
        return this;
    }


    public MultipartFile getPictures() {
        return pictures;
    }

    public EventBindingModel setPictures(MultipartFile pictures) {
        this.pictures = pictures;
        return this;
    }
}
