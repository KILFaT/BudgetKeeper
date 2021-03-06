package com.kilfat.database.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @Column(name = "USER_NAME",
            nullable = false,
            length = 20,
            unique = true)
    private String username;

//    private byte[] encryptedPassword;


    @Column(name = "PASSWORD",
            nullable = false,
            length = 60)
//    @Type(type = "encryptedString")
    private String password;

    @Column(name = "EMAIL",
            length = 80)
    private String email;

    @Column(name = "IMAGE")
    @Type(type = "binary")
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email, byte[] image,
                Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.userRole = userRole;
    }

    public User(String username, String password, String email, byte[] image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
//        if (password == null) {
//            password = CryptoUtils.decrypt(encryptedPassword);
//        }
        return password;
    }

    public void setPassword(String password) {
//        this.encryptedPassword = CryptoUtils.encrypt(password);
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

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}