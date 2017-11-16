package com.hw.service.user;

import com.hw.exception.UserAlreadyExists;
import com.hw.exception.UserNotFoundException;
import com.hw.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws UserAlreadyExists;

    Optional<User> findById(Long id);

    User getCurrentUser(HttpServletRequest request) throws UserNotFoundException;

    void changePassword(HttpServletRequest request, String currentPassword, String newPassword) throws UserNotFoundException;

    String getTokenUserInfo();

    void delete(Long id);

    void delete(User user);
}
