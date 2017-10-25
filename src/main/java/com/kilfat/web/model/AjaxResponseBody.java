package com.kilfat.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.kilfat.web.jsonview.Views;

import java.util.List;

public class AjaxResponseBody {
    @JsonView(Views.Public.class)
    private String message;

    @JsonView(Views.Public.class)
    private String statusCode;

    @JsonView(Views.Public.class)
    private List<UserDTO> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<UserDTO> getResult() {
        return result;
    }

    public void setResult(List<UserDTO> result) {
        this.result = result;
    }
}
