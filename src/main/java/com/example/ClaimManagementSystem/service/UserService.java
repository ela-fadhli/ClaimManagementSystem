package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.model.dto.request.UserUpdateDTO;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);
    User updateUser(String userUuid, UserUpdateDTO updatedUser);
    Optional<User> findById(long id);
    User findByUuid(String userUuid);
}