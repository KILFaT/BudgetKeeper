package com.kilfat.database.service;

import com.kilfat.database.entity.User;
import com.kilfat.database.repository.UserRepository;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Secured({"ADMIN", "USER"})
    @Transactional(readOnly = true)
    public com.kilfat.database.entity.User getUser(String userName) {
        return userRepository.findById(userName).orElseThrow(() -> new EntityNotFoundException(User.class, userName));
    }

    @Secured("ADMIN")
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Secured("ADMIN")
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Secured("ADMIN")
    public void deleteUser(String userName) {
        userRepository.deleteById(userName);
    }
}
