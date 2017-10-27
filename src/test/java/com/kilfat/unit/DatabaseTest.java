package com.kilfat.unit;

import com.kilfat.config.DataConfigProfile;
import com.kilfat.config.HSQLConfig;
import com.kilfat.config.TestConfig;
import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.entity.enums.AccountType;
import com.kilfat.database.service.AccountService;
import com.kilfat.database.service.UserService;
import com.kilfat.exception.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles(DataConfigProfile.HSQLDB)
@Transactional
@ContextConfiguration(classes = {HSQLConfig.class, TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseTest {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void userBaseOperations() {
        User user = new User("Pavel", "password123");
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getName(), user.getName());
        User userFromDb = userService.getUser(savedUser.getId());
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getName(), user.getName());
    }

    @Test
    public void accountBaseOperations() {
        User user = new User("Oleg", "password123");
        User savedUser = userService.saveUser(user);
        Account account = new Account(savedUser, AccountType.CASH, 1200);
        Account savedAccount = accountService.saveAccount(account);
        assertNotNull(savedAccount);
        assertEquals(savedAccount.getUser(), account.getUser());
        Account accountFromDb = accountService.getAccount(savedAccount.getId());
        assertNotNull(accountFromDb);
        assertEquals(accountFromDb.getUser(), account.getUser());
        accountService.deleteAccount(account);
        expectedException.expect(EntityNotFoundException.class);
        accountService.getAccount(savedAccount.getId());
    }
}
