package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.User;

public interface UserService {

    User getUser(String userName);

    User saveUser(User user);

    void deleteUser(User user);

    void deleteUser(String userName);
}
