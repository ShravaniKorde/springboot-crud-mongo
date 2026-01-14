package com.example.crud_operations.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.crud_operations.model.User;
import com.example.crud_operations.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    

    public User create(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(String id) {
        return repo.findById(id).orElse(null);
    }

    public User update(String id, User user) {
        user.setId(id);
        return repo.save(user);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
