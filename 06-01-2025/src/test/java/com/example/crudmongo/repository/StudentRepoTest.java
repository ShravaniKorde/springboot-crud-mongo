package com.example.crudmongo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.example.crudmongo.model.Student;

@DataMongoTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo repository;

    @Test
    public void testSaveAndFindStudent() {
        // Arrange
        Student student = new Student("Test User", "test@example.com");

        // Act
        Student savedStudent = repository.save(student);
        Student found = repository.findById(savedStudent.getId()).orElse(null);

        // Assert
        assertNotNull(found);
        assertEquals("Test User", found.getName());
    }
}
