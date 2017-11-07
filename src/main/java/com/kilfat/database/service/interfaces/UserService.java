package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public interface UserService {

    static String getCurrentUserName() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return principal.getName();
    }

    User getUser(String userName);

    User saveUser(User user);

    void deleteUser(User user);

    void deleteUser(String userName);
}
