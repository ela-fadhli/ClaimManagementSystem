package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ClaimManagementSystem.config.SecurityConfig;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email already taken");
        }
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalStateException("Username already taken");
        }

        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (updatedUser.getUserFirstName() != null) {
            existingUser.setUserFirstName(updatedUser.getUserFirstName());
        }
        if (updatedUser.getUserLastName() != null) {
            existingUser.setUserLastName(updatedUser.getUserLastName());
        }
        if (updatedUser.getUserName() != null) {
            existingUser.setUserName(updatedUser.getUserName());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        return userRepository.save(existingUser);
    }
    
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

}