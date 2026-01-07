package com.example.crudmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crudmongo.model.Student;

public interface StudentRepo extends MongoRepository<Student, String> {
}
