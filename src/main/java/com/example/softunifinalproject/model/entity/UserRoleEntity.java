package com.example.softunifinalproject.model.entity;

import com.example.softunifinalproject.model.entity.enums.UserRolesEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity {

    private UserRolesEnum role;

    public UserRoleEntity() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRolesEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRolesEnum role) {
        this.role = role;
        return this;
    }
}
