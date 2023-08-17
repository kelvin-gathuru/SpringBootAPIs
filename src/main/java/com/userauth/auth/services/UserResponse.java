package com.userauth.auth.services;

public class UserResponse {
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    private String username;
    private String email;

    public UserResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // ... Getter methods ...
}
