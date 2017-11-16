package com.hw.controller.api;

import com.hw.exception.UserAlreadyExists;
import com.hw.model.User;
import com.hw.service.user.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    private User user;
    private User newUserForRegistration;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void register() throws UserAlreadyExists, CloneNotSupportedException {
        user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("ttt");
        user.setPassword("ttt");
        user.setPhoneNumber("test");

        newUserForRegistration = new User();
        newUserForRegistration.setFirstName("NewUser");
        newUserForRegistration.setLastName("NewUser");
        newUserForRegistration.setEmail("NewUser");
        newUserForRegistration.setPassword("NewUser");
        newUserForRegistration.setPhoneNumber("NewUser");

        userService.registerUser(user);
    }

    @After
    public void deleteUser() {
        userService.delete(user);
    }

    @Test
    public void successLogin() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/login")
                .param("email", "ttt")
                .param("password", "ttt"))
                    .andExpect(status().isOk());
    }

    @Test
    public void incorrectPasswordAfterLogin() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/login")
                .param("email", "ttt")
                .param("password", "incorrectPassword"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void successRegistration() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/register")
                .content(saveRequestJsonString(newUserForRegistration))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
    public String saveRequestJsonString(User user) {
        return "{" +
                "\"email\":\"" + user.getEmail()+ '\"' +
                ",\"password\":\"" + user.getPassword() + '\"' +
                ",\"firstName\":\"" + user.getFirstName() + '\"' +
                ",\"lastName\":\"" + user.getLastName() + '\"' +
                ",\"phoneNumber\":\"" + user.getPhoneNumber() + '\"' +
                '}';
    }
}