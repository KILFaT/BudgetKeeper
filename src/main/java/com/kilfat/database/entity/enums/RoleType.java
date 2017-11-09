package com.kilfat.database.entity.enums;

import com.kilfat.exception.TypeNotFoundException;

public enum RoleType {
    USER, ADMIN;

    public static RoleType findType(String text) {
        if (text == null) {
            throw new TypeNotFoundException(String.format("Role type must not be empty!"));
        }
        for (RoleType roleType : RoleType.values()) {
            if (roleType.toString().equalsIgnoreCase(text)) {
                return roleType;
            }
        }
        throw new TypeNotFoundException(String.format("Role type=%s is not found!", text));
    }
}
