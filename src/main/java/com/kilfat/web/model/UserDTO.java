package com.kilfat.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.entity.enums.RoleType;
import com.kilfat.web.model.deserializer.UserDeserializer;

import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(using = UserDeserializer.class)
public class UserDTO {

    private String username;

    @JsonIgnore
    private String password;
    private String email;
    private byte[] image;
    private Set<String> userRole;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    public UserDTO(User user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.email = user.getEmail();
//        this.image = user.getImage();
//        this.userRole = user.getUserRole();
//    }

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setImage(user.getImage());
        Set<String> userRoles = new HashSet<>();
        for (UserRole userRole : user.getUserRole()) {
            userRoles.add(userRole.getRole().toString());
        }
        userDTO.setUserRole(userRoles);
        return userDTO;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setImage(userDTO.getImage());
        Set<UserRole> userRoles = new HashSet<>();
        UserRole tempUserRole;
        for (String userRole : userDTO.getUserRole()) {
            tempUserRole = new UserRole(user, RoleType.valueOf(userRole));
            userRoles.add(tempUserRole);
        }
        user.setUserRole(userRoles);
        return user;
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
