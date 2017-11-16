package com.hw.service.user;

import com.hw.ProblemOffApplication;
import com.hw.exception.UserAlreadyExists;
import com.hw.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProblemOffApplication.class)
public class UserServiceTest {

    private User user;
    private User userClone;

    @Autowired
    private UserService userService;

    @Before
    public void init() throws UserAlreadyExists, CloneNotSupportedException {
        user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test");
        user.setPassword("test");
        user.setPhoneNumber("0665119176");

        userClone = (User) user.clone();

        userService.registerUser(user);
    }

    @After
    public void deleteUser() {
        userService.delete(user);
    }

    @Test(expected = UserAlreadyExists.class)
    public void registerUser() throws Exception {
        userService.registerUser(userClone);
    }
}