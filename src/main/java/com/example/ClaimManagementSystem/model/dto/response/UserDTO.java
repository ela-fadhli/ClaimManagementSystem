package com.example.ClaimManagementSystem.model.dto.response;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

public record UserDTO(
        String uuid,
        String userFirstName,
        String userLastName,
        String userName,
        String password,
        String email) {
}
