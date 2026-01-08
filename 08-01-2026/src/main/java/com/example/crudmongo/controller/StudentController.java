package com.example.crudmongo.controller;

import com.example.crudmongo.model.Student;
import com.example.crudmongo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(service.create(student), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        Student student = service.getById(id);
        return student != null ? ResponseEntity.ok(student)
                               : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update student by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable String id,
            @Valid @RequestBody Student student) {

        Student updated = service.update(id, student);
        return updated != null ? ResponseEntity.ok(updated)
                               : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get students by department")
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Student>> getByDepartment(
            @PathVariable String department) {
        return ResponseEntity.ok(service.getByDepartment(department));
    }
}
