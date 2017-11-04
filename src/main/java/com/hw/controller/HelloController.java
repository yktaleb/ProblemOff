package com.hw.controller;

import com.hw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public Map hello() {
        return Collections.singletonMap("value", "Hello World");
    }
}
