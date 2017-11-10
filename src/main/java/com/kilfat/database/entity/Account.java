package com.kilfat.database.entity;

import com.kilfat.database.entity.enums.AccountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @JoinColumn(name = "USER_NAME",
        nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE",
        nullable = false)
    private AccountType accountType;

    @Column(name = "AMOUNT",
        nullable = false)
    private Integer amount;

    public Account() {
    }

    public Account(User user, AccountType accountType, Integer amount) {
        this.user = user;
        this.accountType = accountType;
        this.amount = amount;
    }

    public void decreaseAmount(Integer value) {
        amount = amount - value;
    }

    public void increaseAmount(Integer value) {
        amount = amount + value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
