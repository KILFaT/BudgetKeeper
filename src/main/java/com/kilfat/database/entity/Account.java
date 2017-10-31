package com.kilfat.database.entity;

import com.kilfat.database.entity.enums.AccountType;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private AccountType accountType;

    @Column(name = "AMOUNT")
    private Integer amount;

    public Account() {
    }

    public Account(User user, AccountType accountType, Integer amount) {
        this.user = user;
        this.accountType = accountType;
        this.amount = amount;
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
