package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.enums.AccountType;
import com.kilfat.web.model.deserializer.AccountDeserializer;

@JsonDeserialize(using = AccountDeserializer.class)
public class AccountDTO {

    private Long id;
    private String userName;
    private String accountType;
    private Integer amount;

    public static AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUserName(account.getUser().getUsername());
        accountDTO.setAccountType(account.getAccountType().toString());
        accountDTO.setAmount(account.getAmount());
        return accountDTO;
    }

    public static Account convertToEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        User user = new User();
        user.setUsername(accountDTO.getUserName());
        account.setUser(user);
        AccountType accountType = AccountType.findType(accountDTO.getAccountType());
        account.setAccountType(accountType);
        account.setAmount(accountDTO.getAmount());
        return account;
    }

    public AccountDTO() {
    }

    public AccountDTO(Long id, String userName, String accountType, Integer amount) {
        this.id = id;
        this.userName = userName;
        this.accountType = accountType;
        this.amount = amount;
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
