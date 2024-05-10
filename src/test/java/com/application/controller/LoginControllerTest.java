package com.application.controller;

import com.application.model.Professor;
import com.application.model.User;
import com.application.services.ProfessorService;
import com.application.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private ProfessorService professorService;

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void loginUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userService.fetchUserByEmailAndPassword("test@example.com", "password")).thenReturn(user);

        String userJson = "{\"email\":\"test@example.com\",\"password\":\"password\"}";

        mockMvc.perform(post("/api/loginuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    void loginDoctor() throws  Exception{
        Professor professor= new Professor();
        professor.setEmail("professorboh@gmail.com");
        professor.setPassword("azerty");
        when(professorService.fetchProfessorByEmailAndPassword("test@example.com", "password")).thenReturn(professor);
        String professorJson = "{\"email\":\"test@example.com\",\"password\":\"password\"}";

        mockMvc.perform(post("/api/loginprofessor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(professorJson))
                .andExpect(status().isOk());
    }

    }


