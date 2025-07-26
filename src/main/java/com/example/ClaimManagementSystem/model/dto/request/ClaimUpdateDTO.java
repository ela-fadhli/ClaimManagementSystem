package com.example.ClaimManagementSystem.model.dto.request;

import com.example.ClaimManagementSystem.ClaimStatus;

import java.util.Date;
import java.util.Optional;

public record ClaimUpdateDTO(
        Optional<String> claimNumber,
        Optional<Date> accidentDate,
        Optional<ClaimStatus> status,
        Optional<Long> contractId
) {
}
