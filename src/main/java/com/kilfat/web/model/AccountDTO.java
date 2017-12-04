package com.kilfat.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.enums.AccountType;
import com.kilfat.web.model.deserializer.AccountDeserializer;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@JsonDeserialize(using = AccountDeserializer.class)
public class AccountDTO {

    private Long id;

    @JsonIgnore
    @NotNull
    private String userName;

    @NotNull
    private String accountType;

    @NotNull
    private Integer amount;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String userName, String accountType, Integer amount) {
        this.id = id;
        this.userName = userName;
        this.accountType = accountType;
        this.amount = amount;
    }

    public static List<AccountDTO> convertToDTO(List<Account> entities) {
        List<AccountDTO> dtos = new ArrayList<>();
        if (entities == null) {
            return dtos;
        }
        for (Account entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }

    public static AccountDTO convertToDTO(Account entity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(entity.getId());
        accountDTO.setUserName(entity.getUser().getUsername());
        accountDTO.setAccountType(entity.getAccountType().toString());
        accountDTO.setAmount(entity.getAmount());
        return accountDTO;
    }

    public static Account convertToEntity(AccountDTO dto) {
        Account account = new Account();
        account.setId(dto.getId());
        User user = new User();
        user.setUsername(dto.getUserName());
        account.setUser(user);
        AccountType accountType = AccountType.findType(dto.getAccountType());
        account.setAccountType(accountType);
        account.setAmount(dto.getAmount());
        return account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
