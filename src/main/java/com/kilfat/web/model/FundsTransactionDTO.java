package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.entity.enums.TransactionType;
import com.kilfat.web.model.deserializer.FundsTransactionDeserializer;
import com.kilfat.web.model.serializer.JsonDateSerializer;

import java.util.Date;
import javax.validation.constraints.NotNull;

@JsonDeserialize(using = FundsTransactionDeserializer.class)
public class FundsTransactionDTO {

    private Long id;

    @NotNull
    private Long accountId;

    private String accountType;

    @NotNull
    private Long categoryId;

    private String categoryName;

    @NotNull
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date date;

    @NotNull
    private String transactionType;

    @NotNull
    private Integer amount;

    public FundsTransactionDTO() {
    }

    public static FundsTransaction convertToEntity(FundsTransactionDTO DTO) {
        FundsTransaction entity = new FundsTransaction();
        entity.setId(DTO.getId());
        Account account = new Account();
        account.setId(DTO.getAccountId());
        entity.setAccount(account);
        entity.setAmount(DTO.getAmount());
        Category category = new Category();
        category.setId(DTO.getCategoryId());
        entity.setCategory(category);
        entity.setDate(DTO.getDate());
        TransactionType transactionType = TransactionType.findType(DTO.getTransactionType());
        entity.setTransactionType(transactionType);
        return entity;
    }

    public static FundsTransactionDTO convertToDTO(FundsTransaction entity) {
        FundsTransactionDTO dto = new FundsTransactionDTO();
        dto.setId(entity.getId());
        dto.setAccountId(entity.getAccount().getId());
        dto.setAmount(entity.getAmount());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setDate(entity.getDate());
        dto.setTransactionType(entity.getTransactionType().toString());
        dto.setAccountType(entity.getAccount().getAccountType().toString());
        dto.setCategoryName(entity.getCategory().getName());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
