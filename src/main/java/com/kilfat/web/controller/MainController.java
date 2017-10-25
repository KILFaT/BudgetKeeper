package com.kilfat.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kilfat.web.jsonview.Views;
import com.kilfat.web.model.AjaxResponseBody;
import com.kilfat.web.model.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class MainController {
    List<String> someList;

    @JsonView(Views.Public.class)
    @RequestMapping(value = "some")
    public AjaxResponseBody getSome(@RequestBody String some) {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        if (some.equalsIgnoreCase("tadaam")) {
            responseBody.setMessage("taadaaam");
            responseBody.setResult(Arrays.asList(new UserDTO("asd", "asd", "asda")));
        } else {
            responseBody.setMessage("WRONG! Not tadaam");
            responseBody.setStatusCode("404");
        }
        return responseBody;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "Wonderful!";
    }
}
