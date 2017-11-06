package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Component
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

//    @Autowired
//    public UserDetailServiceImpl(UserService userService) {
//        this.userService = userService;
//    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUser(userName);
        Set<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                          Set<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private Set<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
        }
        return grantedAuthorities;
    }
}