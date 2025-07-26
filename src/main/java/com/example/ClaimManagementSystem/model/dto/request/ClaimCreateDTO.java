package com.example.ClaimManagementSystem.model.dto.request;

import com.example.ClaimManagementSystem.ClaimStatus;

import java.util.Date;

public record ClaimCreateDTO(
        String claimNumber,
        Date accidentDate,
        ClaimStatus status,
        Long contractId
) {
}
