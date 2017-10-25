package com.kilfat.exception;

public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException(Long id) {
        this.message = String.format("User with id=%s is not found!", id);
    }
}
