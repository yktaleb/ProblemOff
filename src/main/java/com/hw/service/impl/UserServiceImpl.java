package com.hw.service.impl;

import com.hw.exception.UserAlreadyExists;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.repository.RoleRepository;
import com.hw.repository.UserRepository;
import com.hw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registerUser(User user) throws UserAlreadyExists {
        try {
            setUserRoles(user, Collections.singleton("USER_ROLE"));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExists("User already exists");
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("user " + username + " was not found!"));
    }

    private void setUserRoles(User user, Set<String> roles) {
        user.setRoles(new HashSet<>());
        roles.forEach(name -> {
            Role role = roleRepository.findByName(name);
            if (role != null) {
                user.getRoles().add(role);
            } else {
//                log.error("Cannot find role " + roleName);
            }
        });
    }
}
