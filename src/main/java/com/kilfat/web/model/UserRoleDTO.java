package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.entity.enums.RoleType;
import com.kilfat.web.model.deserializer.UserRoleDeserializer;

@JsonDeserialize(using = UserRoleDeserializer.class)
public class UserRoleDTO {

    private Long userRoleId;
    private String user;
    private String role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Long userRoleId, String user, String role) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.role = role;
    }

    public static UserRole convertToEntity(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(userRoleDTO.getUserRoleId());
        User user = new User();
        user.setUsername(userRoleDTO.getUser());
        userRole.setUser(user);
        RoleType roleType = RoleType.findType(userRoleDTO.getRole());
        userRole.setRole(roleType);
        return userRole;
    }

    public static UserRoleDTO convertToDTO(UserRole userRole) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(userRole.getUserRoleId());
        userRoleDTO.setUser(userRole.getUser().getUsername());
        userRoleDTO.setRole(userRole.getRole().toString());
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
