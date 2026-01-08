package com.example.crudmongo.controller;

import com.example.crudmongo.model.Student;
import com.example.crudmongo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll(); // Clear DB before each test
    }

    @Test
    void shouldCreateStudent() throws Exception {
        Student student = new Student("John Doe", "john@example.com", "CS");

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.department").value("CS"));
    }

    @Test
    void shouldGetAllStudents() throws Exception {
        repository.save(new Student("Jane Doe", "jane@example.com", "IT"));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Jane Doe"));
    }

    @Test
    void shouldGetStudentById() throws Exception {
        Student saved = repository.save(new Student("Alice", "alice@example.com", "ECE"));

        mockMvc.perform(get("/students/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/students/{id}", "invalid-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        Student saved = repository.save(new Student("Bob", "bob@example.com", "ME"));
        saved.setName("Bob Updated");
        saved.setDepartment("Civil");

        mockMvc.perform(put("/students/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob Updated"))
                .andExpect(jsonPath("$.department").value("Civil"));
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        Student saved = repository.save(new Student("Charlie", "charlie@example.com", "CS"));

        mockMvc.perform(delete("/students/{id}", saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingInvalidId() throws Exception {
        mockMvc.perform(delete("/students/{id}", "invalid-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetStudentsByDepartment() throws Exception {
        repository.save(new Student("David", "david@example.com", "CS"));
        repository.save(new Student("Eve", "eve@example.com", "IT"));

        mockMvc.perform(get("/students/department/{department}", "CS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("David"));
    }
}
