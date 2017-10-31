package com.kilfat.web.controller;

import com.kilfat.web.model.AjaxResponseBody;
import com.kilfat.web.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class MainController {
    List<String> someList;

//    @JsonView(Views.Public.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "some")
    public AjaxResponseBody getSome(@RequestBody String some) {
        AjaxResponseBody<UserDTO> responseBody = new AjaxResponseBody();
        if (some.equalsIgnoreCase("tadaam")) {
            responseBody.setMessage("taadaaam");
//            responseBody.setResult(Arrays.asList(new UserDTO("asd", "asd", "asda")));
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
