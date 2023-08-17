package com.userauth.auth.controllers;

import com.userauth.auth.dto.LoginAuth;
import com.userauth.auth.entities.User;
import com.userauth.auth.repositories.UserRepository;
import com.userauth.auth.services.JwtService;
import com.userauth.auth.services.UserResponse;
import com.userauth.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/users")
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(new UserResponse(user.getName(), user.getEmail()));
        }

        return userResponses;
    }

    @PostMapping("/add/user")
    public ResponseEntity<Object> addNewUser(@RequestBody User userInfo) {
        return service.addUser(userInfo);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody LoginAuth authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
