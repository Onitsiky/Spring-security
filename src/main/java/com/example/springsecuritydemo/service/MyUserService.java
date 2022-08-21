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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class MyUserService implements UserDetailsService {
    @Autowired
    private MyUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public String createUser(MyUser user){
        String encryptPasswd = getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptPasswd);
        userRepository.save(user);
        return "User Saved !";

    }
}
