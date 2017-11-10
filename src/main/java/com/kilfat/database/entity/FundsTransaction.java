package com.kilfat.database.entity;

import com.kilfat.database.entity.enums.TransactionType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FUNDS_TRANSACTION")
public class FundsTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID",
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE",
        nullable = false,
        length = 10)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE",
        nullable = false)
    private TransactionType transactionType;

    @Column(name = "AMOUNT",
        nullable = false)
    private Integer amount;

    public FundsTransaction() {
    }

    public FundsTransaction(Account account, Category category, Date date, TransactionType transactionType,
                            Integer amount) {
        this.account = account;
        this.category = category;
        this.date = date;
        this.transactionType = transactionType;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}