package com.tuvarna.mvpwarehouse.service.impl;

import com.tuvarna.mvpwarehouse.dto.RegisterRequest;
import com.tuvarna.mvpwarehouse.exception.NotFoundException;
import com.tuvarna.mvpwarehouse.model.Role;
import com.tuvarna.mvpwarehouse.model.User;
import com.tuvarna.mvpwarehouse.repository.IUserRepository;
import com.tuvarna.mvpwarehouse.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequest request) {
        String username = request.getUsername().trim();
        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        Role role;
        try {
            role = Role.valueOf(request.getRole().trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid role. Allowed: ADMIN, AGENT");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        return repository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public boolean exists(String username) {
        return repository.existsByUsername(username.trim());
    }
}
