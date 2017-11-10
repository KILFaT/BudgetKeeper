package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;
import com.kilfat.web.model.deserializer.TemplateDeserializer;

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
        return template;
    }

    public static TemplateDTO convertToDTO(Template entity) {
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setId(entity.getId());
        templateDTO.setAccountId(entity.getAccount().getId());
        templateDTO.setAmount(entity.getAmount());
        templateDTO.setCategoryId(entity.getCategory().getId());
        return templateDTO;
    }


    public TemplateDTO() {
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
}
