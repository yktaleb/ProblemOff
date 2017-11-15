package com.hw.controller.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
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
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                response.setHeader(TOKEN_NAME, tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14)));
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

        Map<String, Object> responseMap = new HashMap();
        responseMap.put("message", message);
        responseMap.put("roles",
                user.getRoles()
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList())
        );
        responseMap.put("firstName", user.getFirstName());
        responseMap.put("lastName", user.getLastName());

        String encode = tokenHandler.encode(responseMap);
        tokenHandler.decode(encode);

        return ResponseEntity
                .status(status)
                .body(responseMap);
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


}
