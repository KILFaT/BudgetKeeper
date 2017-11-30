package com.kilfat.database.entity;

import com.kilfat.database.entity.enums.TransactionType;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID",
        nullable = false,
        unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID",
        nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID",
        nullable = false)
    private Category category;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE",
        nullable = false,
        length = 10)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE",
        nullable = false)
    private TransactionType transactionType;

    public Template() {
    }

    public Template(Account account, Category category, Integer amount, Date date,
                    TransactionType transactionType) {
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
