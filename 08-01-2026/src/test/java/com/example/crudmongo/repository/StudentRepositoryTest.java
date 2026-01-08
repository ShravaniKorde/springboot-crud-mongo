package com.example.crudmongo.repository;

import com.example.crudmongo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll(); // Clear DB before each test
    }

    @Test
    void testSaveAndFindById() {
        Student student = new Student("Test User", "test@example.com", "CS");

        Student saved = repository.save(student);
        Optional<Student> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Test User", found.get().getName());
        assertEquals("test@example.com", found.get().getEmail());
        assertEquals("CS", found.get().getDepartment());
    }

    @Test
    void testFindByEmail() {
        Student student = new Student("Alice", "alice@example.com", "IT");
        repository.save(student);

        Optional<Student> found = repository.findByEmail("alice@example.com");

        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
    }

    @Test
    void testFindByDepartment() {
        Student s1 = new Student("Bob", "bob@example.com", "ME");
        Student s2 = new Student("Charlie", "charlie@example.com", "ME");
        Student s3 = new Student("David", "david@example.com", "CS");

        repository.save(s1);
        repository.save(s2);
        repository.save(s3);

        List<Student> meStudents = repository.findByDepartment("ME");

        assertEquals(2, meStudents.size());
        assertTrue(meStudents.stream().anyMatch(s -> s.getName().equals("Bob")));
        assertTrue(meStudents.stream().anyMatch(s -> s.getName().equals("Charlie")));
    }

    @Test
    void testSearchByName() {
        Student s1 = new Student("Alice Wonderland", "alice@example.com", "IT");
        Student s2 = new Student("Bob Builder", "bob@example.com", "ME");
        repository.save(s1);
        repository.save(s2);

        List<Student> results = repository.searchByName("Alice");

        assertEquals(1, results.size());
        assertEquals("Alice Wonderland", results.get(0).getName());
    }
}
