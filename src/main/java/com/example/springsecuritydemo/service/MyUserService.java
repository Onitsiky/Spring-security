package com.example.springsecuritydemo.service;


import com.example.springsecuritydemo.model.MyUser;
import com.example.springsecuritydemo.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {
    @Autowired
    private MyUserRepository userRepository;
    private final static String not_found = "User is not found";
    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return myUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(not_found, username))) ;
    }

    public void createUser(UserDetails user){
        userRepository.save((MyUser) user);
    }
}
