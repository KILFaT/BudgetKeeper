package com.kilfat.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(username = "USER_ID", nullable = false, unique = true)
//    private Long id;

    @Id
    @Column(name = "USER_NAME",
        nullable = false,
        length = 20,
        unique = true)
    @NotNull
    @Size(min = 5,
        max = 20)
    private String username;

//    private byte[] encryptedPassword;


    @JsonIgnore
    @NotNull
    @Size(min = 6,
        max = 60)
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