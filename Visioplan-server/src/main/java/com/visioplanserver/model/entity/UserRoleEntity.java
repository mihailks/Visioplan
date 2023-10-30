package com.visioplanserver.model.entity;

import com.visioplanserver.model.entity.enums.RolesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    public UserRoleEntity() {
    }

    public RolesEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RolesEnum role) {
        this.role = role;
        return this;
    }


}
