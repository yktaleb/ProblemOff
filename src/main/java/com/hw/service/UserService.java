package com.hw.service;

import com.hw.exception.UserAlreadyExists;
import com.hw.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws UserAlreadyExists;

    Optional<User> findById(Long id);
}
