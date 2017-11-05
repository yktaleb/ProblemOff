package com.hw.service.impl;

import com.hw.entity.User;
import com.hw.exception.UserAlreadyExists;
import com.hw.repository.UserRepository;
import com.hw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) throws UserAlreadyExists {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExists("User already exists");
        }
    }
}
