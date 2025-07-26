package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.model.dto.request.UserUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.UserDTO;
import com.example.ClaimManagementSystem.model.mapper.UserMapper;
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
    @Autowired
    private UserMapper userMapper;


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

    public User updateUser(String userUuid, UserUpdateDTO updatedUser) {
        Long userId = userRepository.findByUuid(userUuid).getId();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        return userRepository.save(userMapper.ToEntity(updatedUser, existingUser));
    }
    
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public User findByUuid(String userUuid) {
        return userRepository.findByUuid(userUuid);
    }
}