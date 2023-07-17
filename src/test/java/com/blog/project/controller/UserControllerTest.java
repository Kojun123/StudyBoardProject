package com.blog.project.controller;


import com.blog.project.domain.Users;
import com.blog.project.dto.user.UserCreateForm;
import com.blog.project.repository.UserRepository;
import com.blog.project.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeEach(){
        UserCreateForm userForm = new UserCreateForm();
        userForm.setUserName("tester1");
        userForm.setEmail("tester@gmail.com");
        userForm.setPassword1("1234");
        userService.createUser(userForm.getUserName(), userForm.getEmail(), userForm.getPassword1());
    }

    @AfterEach
    void clean(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception{
        mockMvc.perform(post("/user/login")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("tester1"));


    }


    @Test
    @DisplayName("로그아웃 성공")
    void logout() throws Exception {
        mockMvc.perform(post("/user/logout") // (1)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/")) // (2)
                .andExpect(unauthenticated()); // (3)
    }
}
