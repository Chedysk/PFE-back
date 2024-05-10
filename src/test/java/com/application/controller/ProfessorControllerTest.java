package com.application.controller;

import com.application.model.Professor;
import com.application.model.User;
import com.application.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfessorControllerTest {
    @Mock
    private ProfessorService professorService;
    @Mock
    private CourseService courseService;
    @Mock
    private ChapterService chapterService;
    @Mock
    private WishlistService wishlistService;

    private MockMvc mockMvc;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }


    @Test
    void getProfessorList() throws Exception {
        List<Professor> professors=new ArrayList<>();
        Professor professor1=new Professor();
        Professor professor2 = new Professor();
        professor2.setEmail("prog@gmail.com");
        professor1.setEmail("prof@gmai.com");
        professor1.setPassword("azerty");
        professor2.setPassword("azerty");
        professors.add(professor1);
        professors.add(professor2);
        when(professorService.getAllProfessors()).thenReturn(professors);

        ResponseEntity<List<Professor>> responseEntity = professorController.getProfessorList();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(professors, responseEntity.getBody());
    }
}