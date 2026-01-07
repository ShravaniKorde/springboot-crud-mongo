package com.example.crudmongo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.crudmongo.model.Student;
import com.example.crudmongo.repository.StudentRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepo repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll(); 
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        String studentJson = "{\"name\": \"John Doe\", \"email\": \"john@example.com\"}";

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(studentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        repository.save(new Student("Jane Doe", "jane@example.com"));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
