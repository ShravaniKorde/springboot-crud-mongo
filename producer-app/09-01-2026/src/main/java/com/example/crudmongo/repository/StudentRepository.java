package com.example.crudmongo.repository;

import com.example.crudmongo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    // Derived query
    Optional<Student> findByEmail(String email);

    // Derived query
    List<Student> findByDepartment(String department);

    // Example custom query (optional but good practice)
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Student> searchByName(String name);
}
