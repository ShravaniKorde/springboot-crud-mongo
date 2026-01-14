package com.example.crud_operations.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.crud_operations.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
