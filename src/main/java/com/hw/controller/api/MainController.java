package com.hw.controller.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hw.exception.UserAlreadyExists;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.service.user.UserService;
import com.hw.util.security.TokenHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class MainController {

    public static final String TOKEN_NAME = "X-Auth-Token";
    public static final String USER = "user";

    private final TokenHandler tokenHandler;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MainController(TokenHandler tokenHandler, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenHandler = tokenHandler;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestParam String email,
                                @RequestParam String password,
                                HttpServletResponse response) {
        HttpStatus status = null;
        String message = null;
        Map<String, Object> responseMap = new HashMap();
        try {
            User user = (User) userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
//                request.getSession().setAttribute(TOKEN_NAME, tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14)));
//                request.getSession().setAttribute(
//                        USER,
//                        UserFront
//                                .builder()
//                                .firtName(user.getFirstName())
//                                .lastName(user.getLastName())
//                                .roles(user.getRoles()));
//                Cookie cookie = new Cookie(TOKEN_NAME, tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14)));
//                response.addCookie(cookie);
                response.setHeader(TOKEN_NAME, tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14)));
                responseMap.put(
                        USER,
                        new UserFront(user.getFirstName(), user.getLastName(), user.getRoles()));
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
        responseMap.put("message", message);
        return ResponseEntity
                .status(status)
                .body(responseMap.toString());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Successful registration"));
        } catch (UserAlreadyExists exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", exception.getMessage()));
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserFront {
        private String firstName;
        private String lastName;
        private Set<Role> roles;

        @Override
        public String toString() {
            return "{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", roles={" + roles.stream().map(role -> role.getName()).collect(Collectors.joining("\n")) + "}}";
        }
    }
}
