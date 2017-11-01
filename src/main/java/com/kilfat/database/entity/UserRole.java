package com.kilfat.database.entity;

import com.kilfat.database.entity.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", nullable = false, unique = true)
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NAME", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private RoleType role;

    public UserRole() {
    }

    public UserRole(User user, RoleType role) {
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
