package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.model.dto.request.UserUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.UserDTO;
import com.example.ClaimManagementSystem.model.mapper.UserMapper;
import com.example.ClaimManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userUuid) {
        return ResponseEntity.ok(userMapper.ToDtoMapper(userService.findByUuid(userUuid)));
    }

    @PutMapping("/{userUuid}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userUuid,
            @RequestBody UserUpdateDTO updatedUser
    ) {
        return ResponseEntity.ok(userMapper.ToDtoMapper(userService.updateUser(userUuid, updatedUser)));
    }
}