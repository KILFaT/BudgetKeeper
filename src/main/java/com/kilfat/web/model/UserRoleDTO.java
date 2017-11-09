package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.web.model.deserializer.UserRoleDeserializer;

@JsonDeserialize(using = UserRoleDeserializer.class)
public class UserRoleDTO {

    private Integer userRoleId;
    private String user;
    private String role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Integer userRoleId, String user, String role) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
