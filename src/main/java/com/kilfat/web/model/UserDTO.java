package com.kilfat.web.model;

import com.kilfat.database.entity.User;

public class UserDTO {
    //    @JsonView(Views.Public.class)
    private String username;

    private String password;

    //    @JsonView(Views.Public.class)
    private String email;

//    public UserDTO(String username, String password, String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }

    public UserDTO(User user) {
        this.username = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
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
}
