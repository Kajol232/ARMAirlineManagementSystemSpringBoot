package com.example.muhammad.armairlinemanagementsystem.repository;

import com.example.muhammad.armairlinemanagementsystem.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
