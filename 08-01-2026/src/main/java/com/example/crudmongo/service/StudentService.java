package com.example.crudmongo.service;

import com.example.crudmongo.model.Student;
import com.example.crudmongo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        // prevent duplicate email
        repository.findByEmail(student.getEmail())
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Email already exists");
                });
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Student update(String id, Student student) {

    // prevent duplicate email (except same student)
    repository.findByEmail(student.getEmail())
            .filter(s -> !s.getId().equals(id))
            .ifPresent(s -> {
                throw new IllegalArgumentException("Email already exists");
            });

    return repository.findById(id)
            .map(existing -> {
                existing.setName(student.getName());
                existing.setEmail(student.getEmail());
                existing.setDepartment(student.getDepartment());
                return repository.save(existing);
            })
            .orElse(null);
}

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public List<Student> getByDepartment(String department) {
        return repository.findByDepartment(department);
    }
}
