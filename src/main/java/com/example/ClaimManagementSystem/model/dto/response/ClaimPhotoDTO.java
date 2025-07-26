package com.example.ClaimManagementSystem.model.dto.response;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public record ClaimPhotoDTO(
        String uuid,
        String path,
        String OriginalFileName,
        String contentType,
        Long size,
        Long claimId,
        Date createdAt
) {
}
