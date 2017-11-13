package com.kilfat.config.security.utils;

import static com.kilfat.config.ServiceConstants.RESPONSE_COOKIE_NAME;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CSRFFilter extends OncePerRequestFilter {

    protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

        Cookie cookie = new Cookie(RESPONSE_COOKIE_NAME, token.getToken());
        cookie.setPath("/");

        response.addCookie(cookie);

        filterChain.doFilter(request, response);
    }
}
