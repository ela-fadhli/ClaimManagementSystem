package com.example.ClaimManagementSystem.model.dto.request;

public record LoginRequest(String email, String password) {
    public Object getEmail() {
        return email;
    }

    public Object getPassword() {
        return password;
    }
}
