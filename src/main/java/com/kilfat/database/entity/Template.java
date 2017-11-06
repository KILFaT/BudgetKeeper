package com.kilfat.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID",
        nullable = false,
        unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID",
        nullable = false)
    private Account account;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID",
        nullable = false)
    private Category category;

    @NotNull
    @Column(name = "AMOUNT")
    private Integer amount;

    public Template() {
    }

    public Template(Account account, Category category, Integer amount) {
        this.account = account;
        this.category = category;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
