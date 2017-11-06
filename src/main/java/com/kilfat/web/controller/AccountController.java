package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Account;
import com.kilfat.database.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ServiceConstants.ACCOUNT_PATH)
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{accountId}",
        method = RequestMethod.GET)
    public @ResponseBody
    Account getAccount(
        @PathVariable("accountId")
            Long accountId) {
        return accountService.getAccount(accountId);
    }

    @RequestMapping(value = "{accountId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAccount(
        @PathVariable("accountId")
            Long accountId,
        @Valid
        @RequestBody
            Account account) {
        account.setId(accountId);
        accountService.saveAccount(account);
    }

    @RequestMapping(value = "{accountId}",
        method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(
        @PathVariable("accountId")
            Long accountId) {
        accountService.deleteAccount(accountId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Account createAccount(
        @Valid
        @RequestBody
            Account account) {
        return accountService.saveAccount(account);
    }
}
