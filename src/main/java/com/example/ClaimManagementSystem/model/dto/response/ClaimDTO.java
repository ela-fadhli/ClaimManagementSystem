package com.example.ClaimManagementSystem.model.dto.response;

import com.example.ClaimManagementSystem.ClaimStatus;

import java.util.Date;


public record ClaimDTO(
        String uuid,
        String claimNumber,
        Date creationDate,
        Date accidentDate,
        ClaimStatus status,
        Long contractId
) {
}
