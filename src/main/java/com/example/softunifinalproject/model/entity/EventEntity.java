package com.example.softunifinalproject.model.entity;

import com.example.softunifinalproject.model.entity.enums.SportCategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity extends BaseEntity{

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer maxPeople;
    private Integer attendant;
    private String location;
    private String description;
    private SportCategoryEnum category;
    private List<PictureEntity> pictures;

    public EventEntity() {
        this.pictures = new ArrayList<>();
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public EventEntity setName(String name) {
        this.name = name;
        return this;
    }


    @Column(name = "max_people", nullable = false)
    public Integer getMaxPeople() {
        return maxPeople;
    }

    public EventEntity setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
        return this;
    }

    @Column()
    public Integer getAttendant() {
        return attendant;
    }

    public EventEntity setAttendant(Integer attendant) {
        this.attendant = attendant;
        return this;
    }

    @Column(nullable = false)
    public String getLocation() {
        return location;
    }

    public EventEntity setLocation(String location) {
        this.location = location;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public EventEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public EventEntity setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public EventEntity setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public SportCategoryEnum getCategory() {
        return category;
    }

    public EventEntity setCategory(SportCategoryEnum category) {
        this.category = category;
        return this;
    }

    @OneToMany
    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public EventEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
