package com.kilfat.database.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

//    private byte[] encryptedPassword;


    @Column(name = "PASSWORD")
//    @Type(type = "encryptedString")
    private String password;

    @Column(name = "EMAIL", length = 80)
    private String email;

    @Column(name = "IMAGE")
    @Type(type = "binary")
    private byte[] image;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String email, byte[] image) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}