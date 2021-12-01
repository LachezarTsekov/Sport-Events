package com.example.softunifinalproject.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "partners")
public class PartnerEntity extends BaseEntity {

    private String name;
    private String website;
    private String logo;

    public PartnerEntity() {
    }

    public String getName() {
        return name;
    }

    public PartnerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public PartnerEntity setWebsite(String website) {
        this.website = website;
        return this;
    }


    public String getLogo() {
        return logo;
    }

    public PartnerEntity setLogo(String logo) {
        this.logo = logo;
        return this;
    }
}
