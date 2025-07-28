package com.example.ClaimManagementSystem.service.impl;

import com.example.ClaimManagementSystem.model.Claim;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import com.example.ClaimManagementSystem.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Transactional
    @Override
    public Claim registerClaim(Claim claim) {
        claim.setUuid(UUID.randomUUID().toString());
        return claimRepository.save(claim);
    }

    @Transactional
    @Override
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
    @Override
    public Claim findClaimById(String uuid) {
        return claimRepository.findByUuid(uuid);
    }

    @Override
    public List<Claim> getAllClaims() {
        return  claimRepository.findAll();
    }

    @Override
    public Long findContractIdByUuid(String claimId) {
        return claimRepository.findContractIdByUuid(claimId);
    }
}