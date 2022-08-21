package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.repository.MyUserRepository;
import com.example.springsecuritydemo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private MyUserService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
