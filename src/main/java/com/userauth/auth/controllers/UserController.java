package com.userauth.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userauth.auth.entities.User;
import com.userauth.auth.repositories.UserRepository;

@RestController
@RequestMapping ("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @PostMapping ("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
}
