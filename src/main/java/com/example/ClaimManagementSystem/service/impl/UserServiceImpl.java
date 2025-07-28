package com.example.ClaimManagementSystem.service.impl;


import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.model.dto.request.UserUpdateDTO;
import com.example.ClaimManagementSystem.model.mapper.UserMapper;
import com.example.ClaimManagementSystem.repository.UserRepository;
import com.example.ClaimManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper;


    @Override
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

    @Override
    public User updateUser(String userUuid, UserUpdateDTO updatedUser) {
        Long userId = userRepository.findByUuid(userUuid).getId();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        return userRepository.save(userMapper.ToEntity(updatedUser, existingUser));
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUuid(String userUuid) {
        return userRepository.findByUuid(userUuid);
    }
}