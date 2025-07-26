package com.example.ClaimManagementSystem.model.dto.request;

import java.util.Date;

public record ContractCreateDTO(String contractNumber,
                                Date startDate,
                                Date endDate,
                                String insuredName,
                                String vehiclePlate) {
}
