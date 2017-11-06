package com.hw.controller;

import com.hw.exception.UserAlreadyExists;
import com.hw.model.entity.User;
import com.hw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
//        Map map = new HashMap();
//        try {
//            userService.registerUser(user);
//            map.put("status", HttpStatus.OK);
//            map.put("message", "Successful registration");
//            return map;
//        } catch (DataIntegrityViolationException e) {
//            map.put("status", HttpStatus.BAD_REQUEST);
//            map.put("message", e.getMessage());
//            return map;
//        }
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
