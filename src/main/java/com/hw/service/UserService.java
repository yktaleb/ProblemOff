package com.hw.service;

import com.hw.model.entity.User;
import com.hw.exception.UserAlreadyExists;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void register(User user) throws UserAlreadyExists;

    Optional<User> findById(Long id);
}
