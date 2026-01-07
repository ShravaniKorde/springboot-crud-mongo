package com.example.crudmongo.controller;

import com.example.crudmongo.model.Student;
import com.example.crudmongo.repository.StudentRepo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepo repository;

    public StudentController(StudentRepo repository) {
        this.repository = repository;
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = repository.save(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Get a student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing student by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        return repository.findById(id)
                .map(existing -> {
                    student.setId(id); 
                    Student updated = repository.save(student);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a student by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}