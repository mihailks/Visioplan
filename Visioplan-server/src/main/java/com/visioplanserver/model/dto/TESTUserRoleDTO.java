package com.visioplanserver.model.dto;

public class TESTUserRoleDTO {
    Long userId;
    Long roleId;

    public TESTUserRoleDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public TESTUserRoleDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public TESTUserRoleDTO setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
}
