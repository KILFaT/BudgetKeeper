package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ServiceConstants.USER_PATH)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{userName}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable("userName") String userName) {
        return userService.getUser(userName);
    }

    @RequestMapping(value = "{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable("userName") String userName, @RequestBody User user) {
        user.setUsername(userName);
        userService.saveUser(user);
    }

    @RequestMapping(value = "{userName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
