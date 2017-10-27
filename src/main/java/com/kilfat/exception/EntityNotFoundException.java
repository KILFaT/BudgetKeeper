package com.kilfat.exception;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    public <T> EntityNotFoundException(Class<T> entity, Long id) {
        this.message = String.format("Entity=%s with id=%s is not found!", entity.getName(), id);
    }
}
