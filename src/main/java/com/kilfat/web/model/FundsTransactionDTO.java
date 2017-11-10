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

    @NotNull
    private Long categoryId;

    @NotNull
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date date;

    @NotNull
    private String transactionType;

    @NotNull
    private Integer amount;

    public FundsTransactionDTO() {
    }

    public FundsTransactionDTO(Long id, Long accountId, Long categoryId, Date date, String transactionType,
                               Integer amount) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public static FundsTransaction convertToEntity(FundsTransactionDTO DTO) {
        FundsTransaction fundsTransaction = new FundsTransaction();
        fundsTransaction.setId(DTO.getId());
        Account account = new Account();
        account.setId(DTO.getAccountId());
        fundsTransaction.setAccount(account);
        fundsTransaction.setAmount(DTO.getAmount());
        Category category = new Category();
        category.setId(DTO.getCategoryId());
        fundsTransaction.setCategory(category);
        fundsTransaction.setDate(DTO.getDate());
        TransactionType transactionType = TransactionType.findType(DTO.getTransactionType());
        fundsTransaction.setTransactionType(transactionType);
        return fundsTransaction;
    }

    public static FundsTransactionDTO convertToDTO(FundsTransaction entity) {
        FundsTransactionDTO fundsTransactionDTO = new FundsTransactionDTO();
        fundsTransactionDTO.setId(entity.getId());
        fundsTransactionDTO.setAccountId(entity.getAccount().getId());
        fundsTransactionDTO.setAmount(entity.getAmount());
        fundsTransactionDTO.setCategoryId(entity.getCategory().getId());
        fundsTransactionDTO.setDate(entity.getDate());
        fundsTransactionDTO.setTransactionType(entity.getTransactionType().toString());
        return fundsTransactionDTO;
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
}
