package com.tuvarna.mvpwarehouse.config;

import com.tuvarna.mvpwarehouse.model.Role;
import com.tuvarna.mvpwarehouse.model.User;
import com.tuvarna.mvpwarehouse.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
        if (!userRepository.existsByUsername("agent")) {
            User agent = new User();
            agent.setUsername("agent");
            agent.setPassword(passwordEncoder.encode("agent123"));
            agent.setRole(Role.AGENT);
            userRepository.save(agent);
        }
    }
}

