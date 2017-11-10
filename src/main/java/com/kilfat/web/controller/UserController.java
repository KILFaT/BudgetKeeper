package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.web.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ServiceConstants.USER_PATH)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{userName}",
            method = RequestMethod.GET)
    public @ResponseBody
    UserDTO getUser(@PathVariable("userName") String userName) {
        User user = userService.getUser(userName);
        return UserDTO.convertToDTO(user);
    }

    @RequestMapping(value = "{userName}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable("userName") String userName, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setUsername(userName);
        User user = UserDTO.convertToEntity(userDTO);
        userService.saveUser(user);
    }

    @RequestMapping(value = "{userName}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = UserDTO.convertToEntity(userDTO);
        user = userService.saveUser(user);
        return UserDTO.convertToDTO(user);
    }
}