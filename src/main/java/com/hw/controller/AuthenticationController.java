package com.hw.controller;

import com.hw.model.entity.User;
import com.hw.service.UserService;
import com.hw.util.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class AuthenticationController {

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String email,
                                @RequestParam String password,
                                HttpServletResponse response) {
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(email);
            if (user != null && user.getPassword().equals(password)) {
                response.addHeader("X-Auth-Token",
                        tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14))
                );
                response.setHeader(
                        "X-Auth-Token",
                        tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14))
                );
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Successful authorization"));
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", exception.getMessage()));
        }
    }
}
