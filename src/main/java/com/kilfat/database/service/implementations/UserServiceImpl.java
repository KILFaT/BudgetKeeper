package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.entity.enums.RoleType;
import com.kilfat.database.repository.UserRepository;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.exception.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User getUser(String userName) {
        User user = userRepository.findById(userName).orElseThrow(() -> new EntityNotFoundException(
            String.format("%s with id=%s is not found!", "User", userName)));
        Hibernate.initialize(user.getUserRole());
        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public User saveUser(User user) {
        if (user.getUserRole().isEmpty()) {
            user.getUserRole().add(new UserRole(user, RoleType.USER));
        } else {
            for (UserRole role : user.getUserRole()) {
                role.setUser(user);
            }
        }
        return userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(String userName) {
        userRepository.deleteById(userName);
    }
}
