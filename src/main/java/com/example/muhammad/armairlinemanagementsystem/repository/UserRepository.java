package com.example.muhammad.armairlinemanagementsystem.repository;

import com.example.muhammad.armairlinemanagementsystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String email);
    List<User> findUsersByTitleEquals(String title);
    boolean   existsByUsername(String email);
    User findUserByUsername(String email);
    User findUserById(long id);
}
