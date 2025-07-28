package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.Claim;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ClaimService {

    Claim registerClaim(Claim claim);
    Claim updateClaim(String claimId, Claim updatedClaim);
    Claim findClaimById(String uuid);
    List<Claim> getAllClaims();
    Long findContractIdByUuid(String claimId);
}