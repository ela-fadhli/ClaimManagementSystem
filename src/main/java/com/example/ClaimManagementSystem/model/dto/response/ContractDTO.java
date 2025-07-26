package com.example.ClaimManagementSystem.model.dto.response;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public record ContractDTO(
        String uuid,
        String contractNumber,
        Date startDate,
        Date endDate,
        String insuredName,
        String vehiclePlate
) {
}
