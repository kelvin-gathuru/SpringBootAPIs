package com.userauth.auth.services;

import com.userauth.auth.entities.User;
import com.userauth.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message","user added successfully");
        return ResponseEntity.ok(response);
    }
}
