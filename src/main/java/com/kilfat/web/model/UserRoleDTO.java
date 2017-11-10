package com.kilfat.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.entity.enums.RoleType;
import com.kilfat.web.model.deserializer.UserRoleDeserializer;

import javax.validation.constraints.NotNull;

@JsonDeserialize(using = UserRoleDeserializer.class)
public class UserRoleDTO {

    private Long userRoleId;

    @NotNull
    @JsonIgnore
    private String user;

    @NotNull
    private String role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Long userRoleId, String user, String role) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.role = role;
    }

    public static UserRole convertToEntity(UserRoleDTO dto) {
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(dto.getUserRoleId());
        User user = new User();
        user.setUsername(dto.getUser());
        userRole.setUser(user);
        RoleType roleType = RoleType.findType(dto.getRole());
        userRole.setRole(roleType);
        return userRole;
    }

    public static UserRoleDTO convertToDTO(UserRole entity) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(entity.getUserRoleId());
        userRoleDTO.setUser(entity.getUser().getUsername());
        userRoleDTO.setRole(entity.getRole().toString());
        return userRoleDTO;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
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
