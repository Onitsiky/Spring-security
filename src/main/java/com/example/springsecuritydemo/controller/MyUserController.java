package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.MyUser;
import com.example.springsecuritydemo.repository.MyUserRepository;
import com.example.springsecuritydemo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyUserController {

    @Autowired
    private MyUserRepository userRepository;
    @Autowired
    private MyUserService userService ;

    @PostMapping("/authorized")
    public String addUser(@RequestBody MyUser user){
        return userService.createUser(user);
    }
}
