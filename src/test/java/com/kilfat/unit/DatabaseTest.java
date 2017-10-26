package com.kilfat.unit;

import com.kilfat.config.DataConfigProfile;
import com.kilfat.config.HSQLConfig;
import com.kilfat.config.TestConfig;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.UserService;
import org.junit.Test;
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
    UserService userService;

    @Test
    public void testMarkerMethod() {
        User user = new User("Pavel", "password123");
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getName(), user.getName());
        User userFromDb = userService.getUser(savedUser.getId());
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getName(), user.getName());
    }
}
