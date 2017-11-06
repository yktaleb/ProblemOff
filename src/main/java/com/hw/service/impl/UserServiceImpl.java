package com.hw.service.impl;

import com.hw.model.entity.Role;
import com.hw.model.entity.User;
import com.hw.exception.UserAlreadyExists;
import com.hw.repository.RoleRepository;
import com.hw.repository.UserRepository;
import com.hw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void registerUser(User user) throws UserAlreadyExists {
        Role role = new Role();
        role.setName("USER_ROLE");
        roleRepository.save(role);
        try {
            Set<String> roles = new HashSet<>();
            roles.add("USER_ROLE");
            setUserRoles(user, roles);
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExists("User already exists");
        }
    }

    public void setUserRoles(User user, Set<String> roles) {
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

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("user " + username + " was not found!"));
    }


}
