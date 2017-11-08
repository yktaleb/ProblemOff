package com.hw.controller;

import com.hw.model.User;
import com.hw.service.UserService;
import com.hw.util.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class AuthenticationController {

    public static final String TOKEN_NAME = "X-Auth-Token";

    private final TokenHandler tokenHandler;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationController(TokenHandler tokenHandler, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenHandler = tokenHandler;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String email,
                                @RequestParam String password,
                                HttpServletResponse response) {
        HttpStatus status = null;
        String message = null;
        try {
            User user = (User) userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                response.setHeader(
                        TOKEN_NAME,
                        tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14))
                );
                status = HttpStatus.OK;
                message = "Successful authorization";
            } else {
                status = HttpStatus.BAD_REQUEST;
                message = "Password is wrong";
            }
        } catch (UsernameNotFoundException exception) {
            status = HttpStatus.BAD_REQUEST;
            message = exception.getMessage();
        }
        return ResponseEntity
                .status(status)
                .body(Collections.singletonMap("message", message));
    }
}
