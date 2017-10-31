package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import com.kilfat.web.jsonview.Views;
//import com.kilfat.web.model.AjaxResponseBody;

@RestController
@RequestMapping(value = ServiceConstants.USER_PATH)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        userService.saveUser(user);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

//    @JsonView(Views.Public.class)
//    @RequestMapping(value = "some")
//    public AjaxResponseBody getSome(@RequestBody String some) {
//        AjaxResponseBody<User> responseBody = new AjaxResponseBody();
//        if (some.equalsIgnoreCase("tadaam")) {
//            responseBody.setMessage("taadaaam");
//            responseBody.setResult(Arrays.asList(new UserDTO("asd", "asd", "asda")));
//        } else {
//            responseBody.setMessage("WRONG! Not tadaam");
//            responseBody.setStatusCode("404");
//        }
//        return responseBody;
//    }

}
