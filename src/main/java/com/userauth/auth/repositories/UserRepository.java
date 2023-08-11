package com.userauth.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userauth.auth.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
