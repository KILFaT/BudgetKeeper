package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.web.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Component
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUser(userName);
        return buildUserForAuthentication(user);
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole userRole : user.getUserRole()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
        }
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user) {
        return new UserPrincipal(user.getUsername(), user.getPassword(),
                                 true, true, true, true, getAuthorities(user), user);
    }
}