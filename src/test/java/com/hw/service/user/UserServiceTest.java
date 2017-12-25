package com.hw.service.user;

import com.hw.ProblemOffApplication;
import com.hw.exception.UserAlreadyExistsException;
import com.hw.model.User;
import org.junit.*;
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
    public void init() throws UserAlreadyExistsException, CloneNotSupportedException {
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

    @Test(expected = UserAlreadyExistsException.class)
    @Ignore
    public void failRegisterUser() throws Exception {
        userService.registerUser(userClone);
    }
    
    @Test
    @Ignore
    public void findById() {
        Assert.assertEquals(userService.findById(user.getId()).get().getId(), user.getId());
    }

    @Test
    @Ignore
    public void successLoadUserByUsername() {
        User userDetails = (User) userService.loadUserByUsername(user.getEmail());
        Assert.assertEquals(userDetails.getId(), user.getId());
    }

    @Test(expected = UsernameNotFoundException.class)
    @Ignore
    public void failLoadUserByUsername() {
        userService.loadUserByUsername("userNotFound");
    }
}