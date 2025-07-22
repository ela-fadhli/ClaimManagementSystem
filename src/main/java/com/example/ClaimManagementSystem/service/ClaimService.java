package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.Claim;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClaimService {
    @Autowired
    private final ClaimRepository claimRepository;

    @Transactional
    public Claim registerClaim(Claim claim) {
        claim.setUuid(UUID.randomUUID().toString());
        return claimRepository.save(claim);
    }

    @Transactional
    public Claim updateClaim(String claimId, Claim updatedClaim) {
            Claim existingClaim = claimRepository.findByUuid(claimId);

        if(updatedClaim.getCreationDate() != null) {
            existingClaim.setCreationDate(updatedClaim.getCreationDate());
        }
        if(updatedClaim.getAccidentDate() != null) {
            existingClaim.setAccidentDate(updatedClaim.getAccidentDate());
        }
        if(updatedClaim.getStatus() != null) {
            existingClaim.setStatus(updatedClaim.getStatus());
        }
        if(updatedClaim.getContractId() != null) {
            existingClaim.setContractId(updatedClaim.getContractId());
        }

        return claimRepository.save(existingClaim);
    }

    @Transactional
    public Claim findClaimById(String uuid) {
        return claimRepository.findByUuid(uuid);
    }

    public List<Claim> getAllClaims() {
        return  claimRepository.findAll();
    }

    public Long findContractIdByUuid(String claimId) {
        return claimRepository.findContractIdByUuid(claimId);
    }
}