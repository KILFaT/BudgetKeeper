package com.kilfat.config.security.utils.method;

import com.kilfat.database.entity.User;
import com.kilfat.database.entity.UserRole;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.web.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String name = authentication.getName();
        String password = "";
        List<GrantedAuthority> authorities;

        User currentUser = userService.getUser(name);
        authorities = getAuthorities(currentUser.getUserRole());
        UserPrincipal
            userPrincipal =
            new UserPrincipal(currentUser.getUsername(), currentUser.getPassword(),
                              getAuthorities(currentUser.getUserRole()), currentUser);
        return new UsernamePasswordAuthenticationToken(userPrincipal, password, authorities);
    }

    private List<GrantedAuthority> getAuthorities(Set<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
            }
        }
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}