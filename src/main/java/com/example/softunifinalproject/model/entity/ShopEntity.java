package com.example.softunifinalproject.model.entity;

import com.example.softunifinalproject.model.entity.enums.ShopCategoryeEnum;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table
public class ShopEntity extends BaseEntity {

    private String name;
    private ShopCategoryeEnum category;
    private BigInteger price;
    private Integer count;
    private String description;

    public ShopEntity() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public ShopEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public ShopCategoryeEnum getCategory() {
        return category;
    }

    public ShopEntity setCategory(ShopCategoryeEnum category) {
        this.category = category;
        return this;
    }

    @Column(nullable = false)
    public BigInteger getPrice() {
        return price;
    }

    public ShopEntity setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public ShopEntity setCount(Integer count) {
        this.count = count;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public ShopEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
