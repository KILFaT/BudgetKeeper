package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.interfaces.AccountService;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.web.model.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = ServiceConstants.ACCOUNT_PATH)
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "names",
        method = RequestMethod.GET)
    public @ResponseBody
    List<AccountDTO> getAccounts() {
        User currentUser = UserService.getCurrentUser().getUser();
        return accountService.getAccountsByUser(currentUser);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{accountId}",
        method = RequestMethod.GET)
    public @ResponseBody
    AccountDTO getAccount(
        @PathVariable("accountId")
            Long accountId) {
        Account account = accountService.getAccount(accountId);
        return AccountDTO.convertToDTO(account);
    }

    @RequestMapping(value = "{accountId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAccount(
        @PathVariable("accountId")
            Long accountId,
        @Valid
        @RequestBody
            AccountDTO accountDTO) {
        Account account = AccountDTO.convertToEntity(accountDTO);
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
    AccountDTO createAccount(
        @Valid
        @RequestBody
            AccountDTO accountDTO) {
        Account account = AccountDTO.convertToEntity(accountDTO);
        account = accountService.saveAccount(account);
        return AccountDTO.convertToDTO(account);
    }
}
