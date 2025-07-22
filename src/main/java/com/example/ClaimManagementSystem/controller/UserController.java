package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @RequestBody User updatedUser
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }
}