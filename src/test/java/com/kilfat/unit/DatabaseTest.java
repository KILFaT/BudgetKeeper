package com.kilfat.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.kilfat.config.TestsBase;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.enums.AccountType;
import com.kilfat.database.service.interfaces.AccountService;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.exception.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

public class DatabaseTest extends TestsBase {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Test
    @WithMockUser(username = "admin",
        password = "password123",
        authorities = {"ADMIN"})
    public void userBaseOperations() {
        User user = new User("Pavel", "password123");
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getUsername(), user.getUsername());
        User userFromDb = userService.getUser(savedUser.getUsername());
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getUsername(), user.getUsername());
    }

    @Test
    @WithMockUser(username = "admin",
        password = "password123",
        authorities = {"ADMIN"})
    public void accountBaseOperations() {
        User user = new User("Oleg", "password123");
        User savedUser = userService.saveUser(user);
        Account account = new Account(savedUser, AccountType.CASH, 1200);
        Account savedAccount = accountService.saveAccount(account);
        assertNotNull(savedAccount);
        assertEquals(savedAccount.getUser(), account.getUser());
        Account accountFromDb = accountService.getAccount(savedAccount.getId());
        assertNotNull(accountFromDb);
        assertEquals(accountFromDb.getUser().getUsername(), account.getUser().getUsername());
        accountService.deleteAccount(account);
        expectedException.expect(EntityNotFoundException.class);
        accountService.getAccount(savedAccount.getId());
    }
}
