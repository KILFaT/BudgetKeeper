package com.kilfat.database.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID",
        nullable = false,
        unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID",
        nullable = false)
    private User user;

    @Column(name = "TYPE")
    @NotNull
    private Enum type;

    @Column(name = "AMOUNT")
    @NotNull
    private Integer amount;
}
