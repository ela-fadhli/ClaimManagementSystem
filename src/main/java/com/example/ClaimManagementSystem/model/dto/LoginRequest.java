package com.example.ClaimManagementSystem.model.dto;

public record LoginRequest(String email, String password) {
    public Object getEmail() {
        return email;
    }

    public Object getPassword() {
        return password;
    }
}
