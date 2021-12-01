package com.example.softunifinalproject.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity{

    private BigDecimal loggedUser;
    private BigDecimal anonymous;

    public Donation() {
    }

    @Column(name = "logged_user")
    public BigDecimal getLoggedUser() {
        return loggedUser;
    }

    public Donation setLoggedUser(BigDecimal loggedUser) {
        this.loggedUser = loggedUser;
        return this;
    }

    @Column
    public BigDecimal getAnonymous() {
        return anonymous;
    }

    public Donation setAnonymous(BigDecimal anonymous) {
        this.anonymous = anonymous;
        return this;
    }
}
