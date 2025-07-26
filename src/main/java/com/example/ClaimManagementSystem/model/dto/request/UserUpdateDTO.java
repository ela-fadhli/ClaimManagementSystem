package com.example.ClaimManagementSystem.model.dto.request;

import java.util.Optional;

public record UserUpdateDTO(
        Optional<String> userFirstName,
        Optional<String> userLastName,
        Optional<String> userName,
        Optional<String> password,
        Optional<String> email
) {
}
