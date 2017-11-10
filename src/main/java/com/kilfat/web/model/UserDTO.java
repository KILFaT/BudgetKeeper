package com.kilfat.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.entity.enums.RoleType;
import com.kilfat.web.model.deserializer.UserDeserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(using = UserDeserializer.class)
public class UserDTO {

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 6, max = 60)
    private String password;

    @Size(max = 80)
    private String email;

    private byte[] image;
    private Set<String> userRole;

    public static UserDTO convertToDTO(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(entity.getUsername());
        userDTO.setPassword(entity.getPassword());
        userDTO.setEmail(entity.getEmail());
        userDTO.setImage(entity.getImage());
        Set<String> userRoles = new HashSet<>();
        for (UserRole userRole : entity.getUserRole()) {
            userRoles.add(userRole.getRole().toString());
        }
        userDTO.setUserRole(userRoles);
        return userDTO;
    }

    public static User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setImage(dto.getImage());
        Set<UserRole> userRoles = new HashSet<>();
        UserRole tempUserRole;
        for (String userRole : dto.getUserRole()) {
            tempUserRole = new UserRole(user, RoleType.findType(userRole));
            userRoles.add(tempUserRole);
        }
        user.setUserRole(userRoles);
        return user;
    }

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<String> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<String> userRole) {
        this.userRole = userRole;
    }
}
