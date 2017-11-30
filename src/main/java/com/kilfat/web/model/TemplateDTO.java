package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;
import com.kilfat.database.entity.enums.TransactionType;
import com.kilfat.web.model.deserializer.TemplateDeserializer;
import com.kilfat.web.model.serializer.JsonDateSerializer;

import java.util.Date;
import javax.validation.constraints.NotNull;

@JsonDeserialize(using = TemplateDeserializer.class)
public class TemplateDTO {

    private Long id;

    @NotNull
    private Long accountId;

    @NotNull
    private Long categoryId;

    @NotNull
    private Integer amount;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date date;

    @NotNull
    private String transactionType;

    public TemplateDTO() {
    }

    public static Template convertToEntity(TemplateDTO dto) {
        Template template = new Template();
        template.setId(dto.getId());
        Account account = new Account();
        account.setId(dto.getAccountId());
        template.setAccount(account);
        template.setAmount(dto.getAmount());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        template.setCategory(category);
        template.setDate(dto.getDate());
        template.setTransactionType(TransactionType.findType(dto.getTransactionType()));
        return template;
    }

    public static TemplateDTO convertToDTO(Template entity) {
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setId(entity.getId());
        templateDTO.setAccountId(entity.getAccount().getId());
        templateDTO.setAmount(entity.getAmount());
        templateDTO.setCategoryId(entity.getCategory().getId());
        templateDTO.setDate(entity.getDate());
        templateDTO.setTransactionType(entity.getTransactionType().toString());
        return templateDTO;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
