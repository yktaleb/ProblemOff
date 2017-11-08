package com.hw.service.user;

import com.hw.exception.UserAlreadyExists;
import com.hw.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws UserAlreadyExists;

    Optional<User> findById(Long id);
}
