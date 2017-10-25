package com.kilfat.database.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FUNDS_TRANSACTION")
public class FundsTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false, length = 10)
    private Date date;

    @Column(name = "TRANSACTION_TYPE")
    @NotNull
    private Enum transactionType;

    @Column(name = "AMOUNT")
    @NotNull
    private Integer amount;
}