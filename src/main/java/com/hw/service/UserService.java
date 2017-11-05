package com.hw.service;

import com.hw.entity.User;
import com.hw.exception.UserAlreadyExists;

public interface UserService {
    void register(User user) throws UserAlreadyExists;
}
