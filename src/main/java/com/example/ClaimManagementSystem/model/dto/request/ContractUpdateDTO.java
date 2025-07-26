package com.example.ClaimManagementSystem.model.dto.request;

import java.util.Date;
import java.util.Optional;

public record ContractUpdateDTO(
        Optional<String> contractNumber,
        Optional<Date> startDate,
        Optional<Date> endDate,
        Optional<String> insuredName,
        Optional<String> vehiclePlate
) {
}
