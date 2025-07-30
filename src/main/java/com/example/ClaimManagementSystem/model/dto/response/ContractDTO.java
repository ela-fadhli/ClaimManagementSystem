package com.example.ClaimManagementSystem.model.dto.response;

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
